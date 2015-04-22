package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import util.RosaArrays;
//TODO: I'm using Wagner-Fischer algorithm. Check improvements and Hirschberg's
//TODO: Algorithm is very similar to lcs. Check the connection between them, if any
public class BS046_Edit {
	public static void main(String[] args) {
		Fasta[] fsta = FastaIO.obtainFastaArray(args, 2);
		System.out.println(RosaArrays.editDistance(fsta[0].dna,fsta[1].dna ));
	}
}
