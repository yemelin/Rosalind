package simple;

import rosaIO.Rstring;
import rosaIO.Task;

public class BS008_Prot {
	public static void main(String[] args) {
		Task io = new Task("prot", args);
		io.printer.println(Rstring.rnaToProtein(io.scanner.readLine()));
		io.close();
	}
}
