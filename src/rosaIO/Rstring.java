package rosaIO;

import java.nio.charset.Charset;
import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;

//TODO: just use dictionary class like HashMap instead of all this (de)coding
//TODO: handle errors for corrupted strings
//TODO: move proteins operations to a separate class
//TODO: it's high time to rewrite everything using a dictionary! It will make
//even sorting easier, as comparators could be used then
//TODO: at least, the ugly hack around approximate binsearch has to be fixed
public class Rstring {
	private final static String CODON = 
			"FFLLSSSSYYZZCCZWLLLLPPPPHHQQRRRRIIIMTTTTNNKKSSRRVVVVAAAADDEEGGGG";
	private final static String RNALETTERS = "UCAG";
	public final static String DNALETTERS = "ACGT";
	public static final String STOPCODONS[] = {"UAA", "UAG", "UGA"};
	public static final String STARTCODON = "AUG";
	
	
	public static int countChars(String s, char c) {
		int n=0;
		for (int i = 0; i < s.length(); i++) {
			if (c==s.charAt(i))
				n++;
		}
		return n;
	}
	
	private static int rnaLetterToCode(char c){
		return RNALETTERS.indexOf(c);
	}
	private static char dnaLetterComplement (char c) {
		return DNALETTERS.charAt(DNALETTERS.length()-DNALETTERS.indexOf(c)-1);
	}
	private static String codeToRnaTriplet(int n) {
		StringBuffer sb = new StringBuffer(3);
		sb.setLength(3);
		for (int i = 2; i>=0; i--) {
//			System.out.println("setting char # " + i+" "+sb.length());
			sb.setCharAt(i, RNALETTERS.charAt(n % 4));
			n/=4;
		}
		return sb.toString();
	}

	private static int rnaTripletToCode(String t, int start) {
		int ret = 0;
		int dig = -1;
		if (t.length()-start>2) {
			for (int i = start; i<start+3; i++) {
				if ((dig = rnaLetterToCode(t.charAt(i)))!=-1)
					ret = ret*4 + dig;
				else break;
			}
		}
		if (dig==-1)
			ret = -1;
		return ret;
	}
//-------------------------------------------------------------	
	public static char rnaTripletToProtLetter (String s, int start) {
		int n;
		if ((n = rnaTripletToCode(s,start))!=-1)
			return CODON.charAt(n);
		else 
			return 'Z'; //return STOP on error. Change it later, perhaps.
	}

	public static String[] protLetterToRnaTriplets(final char c) {
//		System.out.println("protLetterToRnaTriplets(" + c + ")");
		int i = 0, j=0;
		if (c=='Z')
			System.out.println("Got it");
		String ret[] = null, buf[] = new String[6];
//		while ((i = nextIndex(CODON, c, i))!=-1) {
		while ((i = CODON.indexOf(c, i))!=-1) {
			buf[j++] = codeToRnaTriplet(i++);
		}
		if (buf[0]!=null) {
			ret = new String[j];
			System.arraycopy(buf, 0, ret, 0, j);
		}
//		System.out.println("ret from pltrt");
		return ret;
	}
	public static String dnaReverseComplement(String dna) {
		StringBuffer sb = new StringBuffer(dna);
		for (int i=0; i<sb.length();i++) {
			sb.setCharAt(i,dnaLetterComplement(sb.charAt(i)));
		}
		return sb.reverse().toString();
	}
//TODO: probably sb.append should be replaced with sb.setCharAt
// and later sb.setlength	
	public static String rnaToProtein(String rna) {
		char prot;
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<rna.length()-2; i+=3) {
			if ((prot = rnaTripletToProtLetter(rna, i))!='Z')
				sb.append(prot);
			else break;
		}
		return sb.toString();
	}
	
//----------------------------------------------------------------
//	Monoisotopic masses handling
//	TODO: check for errors, possibly change the holding data structure from
//	two arrays to something appropriate (like a dictionary)
	private static String keys;
	private static double masses[];
	public static void loadMassTable(){
		String path = RosaIO.TABLEPATH + "monoisotopic_masses.txt";
		List<String> table = RosaIO.readFileToList(path, Charset.defaultCharset());
		StringBuffer sb = new StringBuffer(table.size());
		sb.setLength(table.size());
		masses = new double[table.size()];
		for (int i=0; i<table.size();i++) {
			sb.setCharAt(i, table.get(i).charAt(0));
			masses[i] = Double.parseDouble(table.get(i).split("\\s+", 2)[1]);			
		}
		keys = sb.toString();
	}
	public static double getMass(char prot) {
		return masses[keys.indexOf(prot)];
	}
	public static double getMass(String protein) {
		return getMass(protein, 0, protein.length());
	}
	public static double getMass (String protein, int start, int end) {
		if (end>protein.length())
			return 0;
		double ret = 0f;
		for (int i = start; i < end; i++) {
			ret+=getMass(protein.charAt(i));
		}
		return ret;
	}
	private static boolean _massTableSorted = false;

//	the hack. The problem is, binsearch checks exact values, while mass values
//	may have some precision difference. And since they are primitive double
//	values, binsearch with comparator can't be applied!
	public static char getProtByMass(double mass, double precision) {
		if (!_massTableSorted)
			sortMassTable();
		int retInd = Arrays.binarySearch(masses, mass);
		if (retInd<0) {
			int tmpret = (retInd>=0)? retInd: Math.abs(retInd+1);
//			System.out.println("tmpret = "+ tmpret);
//			System.out.println(Math.abs(mass-masses[tmpret-1])+" "+
//					Math.abs(mass-masses[tmpret]));;
			if (tmpret>0 && Math.abs(mass-masses[tmpret-1])<precision) 
				retInd = tmpret-1;
			else if	(tmpret<masses.length && Math.abs(mass-masses[tmpret])<precision)
				retInd = tmpret;
		}
		if (retInd>=0) {
//			System.out.println("tmp ret:" + retInd);//+ " "+keys.charAt(retInd));
			return keys.charAt(retInd);
		}
		else return (char)-1;
	}
	private static int min (int a, int b) {
		return (a<b)? a:b;
	}
//	private static void merge (double[] d1, int left, int right, int end, double[] d2){
//		int il = left, ir = right;
//		for (int j=left; j<end; j++) {
//			if (il<right && (ir>=end || d1[il]<=d1[ir])) {
//				d2[j] = d1[il];
//				il++;
//			}
//			else {
//				d2[j]=d1[ir];
//				ir++;
//			}
//		}			
//	}
	
	private static void merge (int[] d1, int left, int right, int end, int[] d2){
		int il = left, ir = right;
		for (int j=left; j<end; j++) {
			if (il<right && (ir>=end || masses[ d1[il] ]<=masses[ d1[ir] ])) {
				d2[j] = d1[il];
				il++;
			}
			else {
				d2[j]=d1[ir];
				ir++;
			}
		}
	}
		
	private static void sortMassTable() {
			int len = masses.length;
			double[] buf = new double[len];
			int ind[] = new int[len];
			for (int i=0; i<ind.length; ind[i] = i++);
			int indbuf[] = new int[len];
//			System.out.println("Before cycle");
			for (int w=1; w<len; w*=2) {
				for (int i=0; i<len; i+=w*2) {
//					merge(masses, i, min(i+w, len), min(i+2*w,len), buf);
					merge(ind, i, min(i+w, len), min(i+2*w,len), indbuf);
				}
//				double[] tmp = masses;
//				masses = buf; buf = tmp;
				int tmp2[] = ind;
				ind = indbuf; indbuf=tmp2;
//				RosaArrays.printArray(masses);
//				RosaArrays.printArray(ind);
			}
			StringBuffer sb = new StringBuffer();
			sb.setLength(keys.length());
			
			for (int i = 0; i < keys.length(); i++) {
				sb.setCharAt(i, keys.charAt(ind[i]));
				buf[i] = masses[ind[i]];
			}
			_massTableSorted = true;
			masses = buf;
			keys = sb.toString();
	}

	//----------------------------------------------------------------
//	print array of string
	public static void printArrayString (String as[]) {
		System.out.print("[ ");
		for (int i = 0; i < as.length; i++) {
			System.out.print(as[i]+", ");
		}
		System.out.println(" ]");
	}

	public static double countGC (String s) {
		int count=0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)=='G' || s.charAt(i)=='C')
				count++;
		}
		return (double)(count)/s.length();
	}

	public static double calculateLogProb(String dna, double gcP) {
		double ret = 0;
		for (int i = 0; i < dna.length(); i++) {
			ret += Math.log10(getProbFromGC(dna.charAt(i), gcP));
		}
		return ret;
	}

// probability, that a random dna string is equal to the given one (with single
//	nucleotides probabilities based on gc
	public static double dnaProb (String dna, double gc) {
		return Math.pow(10, calculateLogProb(dna, gc));
	}
	
	public static double getProbFromGC(char c, double gcP) {
		double ret;
		switch (c) {
			case 'A':
			case 'T': ret = (1 - gcP)/2; break;
			case 'G':
			case 'C': ret = gcP/2; break;
			default: ret = 0;
		}
		return ret;
	}
//	---------------------------------

	public static int hamming (String s1, String s2) {
		int ret=0;
		if (s1.length()!=s2.length())
			ret = -1;
		else
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i)!=s2.charAt(i))
					ret++;
			}
		return ret;
	}

	public static void main(String[] args) {
		loadMassTable();
		sortMassTable();
		for (int i = 0; i < keys.length(); i++) {
			System.out.println(keys.charAt(i)+" "+getMass(keys.charAt(i)));			
		}
	}
}
