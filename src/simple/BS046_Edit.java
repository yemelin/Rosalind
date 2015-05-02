package simple;

import rosaIO.Fasta;
import rosaIO.Task;
import util.RosaArrays;
//TODO: I'm using Wagner-Fischer algorithm. Check improvements and Hirschberg's
//TODO: Algorithm is very similar to lcs. Check the connection between them, if any
//TODO: consider adding minimum number of records to read to array-reading functions
public class BS046_Edit {
	public static void main(String[] args) {
		Task io = new Task("edit", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		if (fsta.length==2)
			io.printer.println(RosaArrays.editDistance(fsta[0].dna,fsta[1].dna ));
		else
			System.err.println("Input corrupted");
	}
}
