package simple;

import rosaIO.RosaIO;
import rosaIO.Task;
import util.RosaArrays;

public class BS024_Lgis {
	public static void main(String[] args) {
		Task io = new Task("lgis", args);
// TODO: n seems unneeded, n simplifies things only when solved in C
		int n = io.scanner.readInt();
		int a[] = io.scanner.readIntArray();
		if (a.length==n) {
			RosaIO.printArray(RosaArrays.longestSeq(a, true));	//increasing
			RosaIO.printArray(RosaArrays.longestSeq(a, false));	//decreasing
		}
		else
			System.out.println("Input corrupted");
		io.close();
	}
}
