package bs042_Rear;

import java.util.ArrayList;

import rosaIO.Task;

public class BS052_Sort {

	public static void main(String[] args) {
		Task io = new Task ("sort", args);
		Reversal.PermPair pair = new Reversal.PermPair(
				io.scanner.readLineToIntArray(), 
				io.scanner.readLineToIntArray());
		if (pair.inputOK()) {
			io.printer.println(Reversal.countReversals(pair.from, pair.to));
			ArrayList<BrokenReversal> brl = Reversal.sortingReversals();
			for (int i=brl.size()-2; i>=0; i--) {
				io.printer.printArray(brl.get(i).revPoints);
			}
		}
		else
			System.err.println("Input corrupted");
	}
}
