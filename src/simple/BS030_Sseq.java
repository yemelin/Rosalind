package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.RosaIO;


// http://rosalind.info/problems/sseq/
public class BS030_Sseq {
	public static int [] subSeqIndices (String s, String seq) {
		int [] a = new int[seq.length()];
		for (int i = 0; i < a.length; i++) {
			a[i] = s.indexOf(seq.charAt(i), (i>0)? a[i-1]:0)+1;//1-based
		}
		return a;
	}

	public static void main(String[] args) {
		Fasta[] fsta = FastaIO.obtainFastaArray(args, 2);
		RosaIO.printArray(subSeqIndices(fsta[0].dna, fsta[1].dna));
	}
}
