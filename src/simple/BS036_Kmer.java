package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.Rstring;
import util.RosaArrays;

public class BS036_Kmer {
	public static int[] numToDigs (int n, int radix, int length) {
		int [] a = new int [length];
		for (int i=length-1; i>=0; i--) {
			a[i] = n%radix;
			n = n/radix;
//			System.out.printf("%d ", a[i]);
		}
//		System.out.println();
		return a;
	}
	public static int digsToNum (int digs[], int radix) {
		int ret = 0;
		for (int i=0; i<digs.length; i++) {
			ret=ret*radix+digs[i];
		}
		return ret;
	}
//	TODO: check length
	public static int[] stringToDigs (String s, String alph) {
		int a[] = new int[s.length()];
		for (int i=0; i<a.length; i++) {
			if ((a[i]=alph.indexOf(s.charAt(i)))==-1)
				return null;
		}
		return a;
	}
	
	private static int pow (int n, int exp) {
		int ret = 1;
		for (int i=0; i<exp; i++, ret*=n);
		return ret;
	}
//	TODO: Optimize string handling and repeated char-to-digit conversion
	public static int[] dnaKmerStats(String dna, int k) {
		int a[] = new int [pow(Rstring.DNALETTERS.length(), k)];
		for (int i=0; i<dna.length()-k+1; i++) {
//			int digs[] = stringToDigs(dna.substring(i, i+k), Rstring.DNALETTERS);
//			System.out.print(dna.substring(i, i+k)+" ");
//			RosaArrays.printArray(digs);
			a[digsToNum(stringToDigs(dna.substring(i, i+k), Rstring.DNALETTERS),
					Rstring.DNALETTERS.length())]++;
//			if (i==dna.length()-k)
//				System.out.println("last processed:"+dna.substring(i, i+k));
		}
		
		return a;
	}
	public static void main(String[] args) {
//		util.RosaArrays.printArray(numToDigs(156, 10, 2));
//		int digs[] = {1,2,4};
//		System.out.println(digsToNum(digs,10));
//		RosaArrays.printArray(numToDigs(14, 4, 4));
		Fasta[] fsta = FastaIO.obtainFastaArray(args, 1);
//		System.out.println(fsta[0].dna);
		RosaArrays.printArray(dnaKmerStats(fsta[0].dna, 4));
	}

}
