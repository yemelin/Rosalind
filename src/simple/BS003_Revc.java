//http://rosalind.info/problems/revc/
package simple;

import rosaIO.Task;
import util.Rstring;
public class BS003_Revc {
	
	public static void main(String[] args) {
		Task io = new Task ("revc", args);
		String st = io.scanner.readLine();
		io.printer.print(Rstring.dnaReverseComplement(st));
		io.close();
	}
}
