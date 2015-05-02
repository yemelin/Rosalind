package simple;
// http://rosalind.info/problems/pmch/
import java.math.BigInteger;

import rosaIO.Fasta;
import rosaIO.Rstring;
import rosaIO.Task;
import util.MathStats;
public class BS026_Pmch {

	public static void main(String[] args) {
		Task io = new Task("pmch", args);
		Fasta[] fsta = io.scanner.readFastaArray();
//	TODO: use countchars counting all chars in one run
		int nAU = Rstring.countChars(fsta[0].dna, 'A');
		int nGC = Rstring.countChars(fsta[0].dna, 'C');
		BigInteger nMatch = MathStats.factFall(0, nAU).multiply(MathStats.factFall(0, nGC));
		io.printer.println(nMatch);
		io.close();
	}

}
