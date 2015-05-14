package bs042_Rear;
// TODO: input should be improved. My libraries obviously do not handle empty
// lines efficiently
// TODO: try to implement Breadth-first search algorithms and overcome the 
// memory and complexity issues, rather than using N top reversal that minimize
// breakpoints number (which seems however almost a guarantee)
import java.util.ArrayList;

import rosaIO.Task;
import bs042_Rear.Reversal.PermPair;

public class BS042_Rear {

	public static void main(String[] args) {
		Task io = new Task ("rear", args);
		PermPair tmpPair;
		ArrayList<PermPair> pairs = new ArrayList<>();
		while (io.scanner.hasNextLine()) {
			int[] from = io.scanner.readLineToIntArray();
			int[] to = io.scanner.readLineToIntArray();
			tmpPair = new PermPair(from, to);
			if (tmpPair.inputOK())
				pairs.add(tmpPair);
			else {
				System.out.println("Input really corrupted");
				System.exit(1);
			}				
			if (io.scanner.hasNextLine()) {
				io.scanner.readLine();
			}
		}
		
		int [] output = new int[pairs.size()];
		int i=0;
		for (PermPair pair : pairs) {
			output[i++] = Reversal.countReversals(pair.from, pair.to);
		}
		io.printer.printArray(output);
	}
}
