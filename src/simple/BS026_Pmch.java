package simple;
// http://rosalind.info/problems/pmch/
import java.math.BigInteger;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.RosaIO;
import rosaIO.Rstring;
import util.MathStats;
public class BS026_Pmch {
	public static Fasta[] getInput(String [] args) {
		Fasta[] fst;
		if (args.length>0)
			fst = FastaIO.fileToFastaArray(RosaIO.DATAPATH+args[0]);
		else
			fst = FastaIO.inputToFastaArray();
		return fst;
	}

	public static void main(String[] args) {
		Fasta[] fst = getInput(args);
		if (fst!=null) {
			int nAU = Rstring.countChars(fst[0].dna, 'A');
			int nGC = Rstring.countChars(fst[0].dna, 'C');
//			System.out.println(nAU+" "+nGC);
			BigInteger nMatch = MathStats.factFall(0, nAU).multiply(MathStats.factFall(0, nGC));
			System.out.println(nMatch);
		}
		else
			System.out.println("Input corrupted.");
	}

}
