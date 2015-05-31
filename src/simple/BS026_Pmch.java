// http://rosalind.info/problems/pmch/
package simple;
import java.math.BigInteger;

import rosaIO.Fasta;
import rosaIO.Task;
import util.MathStats;
import util.Rstring;
public class BS026_Pmch {

	public static void main(String[] args) {
		Task io = new Task("pmch", args);
		Fasta[] fsta = io.scanner.readFastaArray();
// count assumes equal numbers for nucleotide and its complement
		int nAU = Rstring.countChar(fsta[0].dna, 'A');
		int nGC = Rstring.countChar(fsta[0].dna, 'C');
		
		BigInteger nMatch = MathStats.factFall(0, nAU).multiply(MathStats.factFall(0, nGC));
		io.printer.println(nMatch);
		io.close();
	}

}
