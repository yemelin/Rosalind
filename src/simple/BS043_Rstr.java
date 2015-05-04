package simple;
//http://rosalind.info/problems/rstr/
import rosaIO.Rstring;
import rosaIO.Task;

public class BS043_Rstr {

	public static void main(String[] args) {
		Task io = new Task("rstr", args);
		int n=io.scanner.readInt();

		double gc=io.scanner.readDouble();
		String dna=io.scanner.readLine();
		if (n<=0 || gc<=0.0)
			System.err.println("Input corrupted.");
		else {
//	probability that a random string is equal to dna
			double dnaProb = Math.pow(10, Rstring.calculateLogProb(dna, gc));
			io.printer.println(1 - Math.pow(1-dnaProb, n));
		}
	}
}
