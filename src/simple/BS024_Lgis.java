//http://rosalind.info/problems/lgis/
package simple;

import rosaIO.Task;
import util.RosaArrays;

public class BS024_Lgis {
	public static void main(String[] args) {
		Task io = new Task("lgis", args);
// TODO: n seems unneeded, n simplifies things only when solved in C
		int n = io.scanner.readInt();
		int a[] = io.scanner.readIntArray();
		if (a.length==n) {
			io.printer.printArray(RosaArrays.longestSeq(a, true));	//increasing
			io.printer.printArray(RosaArrays.longestSeq(a, false));	//decreasing
		}
		else
			System.err.println("Input corrupted");
		io.close();
	}
}
