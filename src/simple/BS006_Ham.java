//http://rosalind.info/problems/ham/
package simple;

import rosaIO.Task;
import util.Rstring;

public class BS006_Ham {
	public static void main(String[] args) {
		Task io = new Task("ham", args);
		String s1 = io.scanner.readLine();
		String s2 = io.scanner.readLine();
		io.printer.println(Rstring.hamming(s1, s2));
		io.close();
	}
}
