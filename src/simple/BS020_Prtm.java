//http://rosalind.info/problems/prtm/
package simple;

import rosaIO.Task;
import util.MassTable;

public class BS020_Prtm {
	public static void main(String[] args) {
		Task io = new Task("prtm", args);
		MassTable.loadMassTable();
		io.printer.println(MassTable.getMass(io.scanner.readLine()));
		io.close();
	}
}
