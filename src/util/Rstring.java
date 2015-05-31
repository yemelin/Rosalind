package util;


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
	public static final double PRECISION = 0.0001;
	
	public static int countChar(String s, char c) {
		int n=0;
		for (int i = 0; i < s.length(); i++) {
			if (c==s.charAt(i))
				n++;
		}
		return n;
	}

	//using String	
	public static int[] countChars(String s, String alph) {
		int ind;
		int a[] = new int[alph.length()];
		for (int i=0; i<s.length(); i++)
			if ((ind = alph.indexOf(s.charAt(i)))!=-1)
				a[ind]++;
		return a;
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
		int i = 0, j=0;
		String ret[] = null, buf[] = new String[6];

		while ((i = CODON.indexOf(c, i))!=-1) {
			buf[j++] = codeToRnaTriplet(i++);
		}
		if (buf[0]!=null) {
			ret = new String[j];
			System.arraycopy(buf, 0, ret, 0, j);
		}
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
////	print array of string
//	public static void printArrayString (String as[]) {
//		System.out.print("[ ");
//		for (int i = 0; i < as.length; i++) {
//			System.out.print(as[i]+", ");
//		}
//		System.out.println(" ]");
//	}

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

	public static final String UNIPROT_URL = "http://www.uniprot.org/uniprot/";

}
