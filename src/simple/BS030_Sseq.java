// http://rosalind.info/problems/sseq/
package simple;

import rosaIO.Fasta;
import rosaIO.Task;


public class BS030_Sseq {
	public static int [] subSeqIndices (String s, String seq) {
		int [] a = new int[seq.length()];
		for (int i = 0; i < a.length; i++) {
			a[i] = s.indexOf(seq.charAt(i), (i>0)? a[i-1]:0)+1;//1-based
		}
		return a;
	}

	public static void main(String[] args) {
		Task io = new Task("sseq", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		if (fsta.length==2)
			io.printer.printArray(subSeqIndices(fsta[0].dna, fsta[1].dna));
		else
			System.err.println("Input corrupted");
		io.close();
	}
}
