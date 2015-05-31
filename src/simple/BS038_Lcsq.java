//http://rosalind.info/problems/lcsq/
package simple;

import rosaIO.Fasta;
import rosaIO.Task;
import util.RosaArrays;
import util.Rstring;
// TODO: encoding Strings and sequences to numbers and back is not the first
// time used here and should be moved to libraries
public class BS038_Lcsq {
	public static int[] stringToDigs (String s, String alph) {
		int a[] = new int[s.length()];
		for (int i=0; i<a.length; i++) {
			if ((a[i]=alph.indexOf(s.charAt(i)))==-1)
				return null;
		}
		return a;
	}
	public static String digsToString (int a[], String alph) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<a.length; i++)
			sb.append(alph.charAt(a[i]));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Task io = new Task("lcsq", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		if (fsta.length==2) {
			int a1 [] = stringToDigs(fsta[0].dna, Rstring.DNALETTERS);
			int a2 [] = stringToDigs(fsta[1].dna, Rstring.DNALETTERS);
			io.printer.println(digsToString(RosaArrays.lcs (a1,a2), Rstring.DNALETTERS));
		}
		else System.err.println("Input corrupted");
		io.close();
	}

}
