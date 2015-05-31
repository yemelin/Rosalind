//http://rosalind.info/problems/fibd/
package simple;
import rosaIO.Task;
import util.Fib;

public class BS011_Fibd {
	public static void main(String[] args) {
		Task io = new Task("fibd",args);
		int n = io.scanner.readInt();
		int k = io.scanner.readInt();
		io.printer.println(Fib.fib_m(n,k));
		io.close();
	}
}
