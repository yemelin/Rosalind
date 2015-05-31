//http://rosalind.info/problems/cat/
package bs033_Cat;

import rosaIO.Fasta;
import rosaIO.Task;

public class BS033_Cat {
	public static void main(String[] args) {
		Task io = new Task ("cat", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		String s = fsta[0].dna;
//		System.out.println(Catalan.catalanDna(s));
		io.printer.println(Catalan.catalanDnaBig(s));
	}
}
