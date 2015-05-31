package util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import rosaIO.RosaScanner;
import rosaIO.Streams;

public class MassTable {
//----------------------------------------------------------------
//	Monoisotopic masses handling
//	TODO: check for errors, possibly change the holding data structure from
//	two arrays to something appropriate (like a dictionary)

// keys and masses represent the mass table. Tough heritage of the C-code	
	private static String keys;
	private static double masses[];
	private static boolean _massTableSorted = false;
	private static boolean _isLoaded = false;

	public static void loadMassTable(){
		if (!_isLoaded) {
			String path = Streams.TABLEPATH + "monoisotopic_masses.txt";
			InputStream is = Streams.obtainInputStream(path);
			List<String> table = new RosaScanner(is).readList();
			StringBuffer sb = new StringBuffer(table.size());
			sb.setLength(table.size());
			masses = new double[table.size()];
			for (int i=0; i<table.size();i++) {
				sb.setCharAt(i, table.get(i).charAt(0));
				masses[i] = Double.parseDouble(table.get(i).split("\\s+", 2)[1]);			
			}
			keys = sb.toString();
			_isLoaded = true;	//just in case, butnever needed
		}
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

// auxiliary for mergeSort
	private static int min (int a, int b) {
		return (a<b)? a:b;
	}

// merge - auxiliary function for mergeSort	
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

// I had to write my own mergeSort for mass table , because instead of
// normal Object representation of the table I used primitive data structures
	private static void sortMassTable() {
		int len = masses.length;
		double[] buf = new double[len];
		int ind[] = new int[len];
		for (int i=0; i<ind.length; ind[i] = i++);
		int indbuf[] = new int[len];

		for (int w=1; w<len; w*=2) {
			for (int i=0; i<len; i+=w*2) {
				merge(ind, i, min(i+w, len), min(i+2*w,len), indbuf);
			}

			int tmp2[] = ind;
			ind = indbuf; indbuf=tmp2;
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
}
