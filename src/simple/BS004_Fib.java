//http://rosalind.info/problems//
package simple;

import rosaIO.Task;
import util.Fib;
public class BS004_Fib {
	public static void main(String[] args) {
		Task io = new Task("fib", args);
		int k = io.scanner.readInt();
		int n = io.scanner.readInt();
		io.printer.println(Fib.fib(n,k));
		io.close();
	}
}
