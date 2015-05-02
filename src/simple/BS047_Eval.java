package simple;

import rosaIO.Rstring;
import rosaIO.Task;

//http://rosalind.info/problems/eval/

public class BS047_Eval {

	public static void main(String[] args) {
		Task io = new Task("eval", args);
		int n = io.scanner.readInt();
		String pattern = io.scanner.readLine();
		double gcs[] = io.scanner.readDoubleArray();
	
//		System.out.println(n+" \n"+java.util.Arrays.toString(gcs)+"\n"+inp);
//		double output[] = new double[gcs.length];
// as we have multiplication, log10 can be used instead direct probabilities
		for (double gc : gcs)
			io.printer.printf("%f ", Math.pow(10, Math.log10(n-pattern.length()+1)+Rstring.calculateLogProb(pattern, gc)));
		io.printer.println();
//		the same result with probabilities, no precision gain
		for (double gc : gcs) {
			System.out.printf("%f ", (n-pattern.length()+1)*Rstring.dnaProb(pattern, gc));
		}
		System.out.println();
	}
	
}
