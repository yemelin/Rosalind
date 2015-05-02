package simple;

import rosaIO.Rstring;
import rosaIO.Task;

public class BS020_Ptrm {
	public static void main(String[] args) {
		Task io = new Task("ptrm", args);
		Rstring.loadMassTable();
		io.printer.println(Rstring.getMass(io.scanner.readLine()));
		io.close();
	}
}
