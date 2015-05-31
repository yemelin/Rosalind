//http://rosalind.info/problems/kmer/
package simple;

import rosaIO.Fasta;
import rosaIO.Task;
import util.Rstring;

// Task is solved through encoding strings to numbers using alphabet as digits
// the precedence order for numbers is therefore the same as for the encoded
// strings. Note, that DNALETTERS are already sorted.
public class BS036_Kmer {
	public static int[] numToDigs (int n, int radix, int length) {
		int [] a = new int [length];
		for (int i=length-1; i>=0; i--) {
			a[i] = n%radix;
			n = n/radix;
		}
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

// TODO: integer power function should be moved to some library
	private static int pow (int n, int exp) {
		int ret = 1;
		for (int i=0; i<exp; i++, ret*=n);
		return ret;
	}

	//	TODO: Optimize string handling and repeated char-to-digit conversion
	public static int[] dnaKmerStats(String dna, int k) {
		int a[] = new int [pow(Rstring.DNALETTERS.length(), k)];
		for (int i=0; i<dna.length()-k+1; i++) {
			a[digsToNum(stringToDigs(dna.substring(i, i+k), Rstring.DNALETTERS),
					Rstring.DNALETTERS.length())]++;
		}		
		return a;
	}
	public static void main(String[] args) {
		Task io = new Task("kmer", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		io.printer.printArray(dnaKmerStats(fsta[0].dna, 4));
		io.close();
	}

}
