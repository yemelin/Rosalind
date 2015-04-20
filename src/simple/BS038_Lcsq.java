package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.Rstring;
import util.RosaArrays;

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
		Fasta[] fsta = FastaIO.obtainFastaArray(args, 2);
		int a1 [] = stringToDigs(fsta[0].dna, Rstring.DNALETTERS);
		int a2 [] = stringToDigs(fsta[1].dna, Rstring.DNALETTERS);
		System.out.println(digsToString(RosaArrays.lcs (a1,a2), Rstring.DNALETTERS));
//		char c = lcs (fsta[0].dna.toCharArray(), fsta[0].dna.toCharArray());
	}

}
