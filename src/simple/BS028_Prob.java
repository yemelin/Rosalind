package simple;

import rosaIO.Rstring;
import rosaIO.Task;

public class BS028_Prob {

	public static void main(String[] args) {
		Task io = new Task("prob", args);
		String dna = io.scanner.readLine();
		double [] gcs = io.scanner.readDoubleArray();
		double [] logProbs = new double [gcs.length];
		for (int i = 0; i < gcs.length; i++) {
			logProbs[i] = Rstring.calculateLogProb(dna, gcs[i]);
		}
		io.printer.printArray(logProbs);
	}
}
