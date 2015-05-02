package simple;

import rosaIO.Task;
import util.Sets;
// TODO: it's high time to organize normal file output!!! 
public class BS051_Seto {

	public static void main(String[] args) {
		Task io = new Task("seto", args);
		int n = io.scanner.readInt(); io.scanner.readLine();
		int[] set1 = io.scanner.readSet();
		int[] set2 = io.scanner.readSet();
		io.printer.printSet((Sets.union(set1, set2)));
		io.printer.printSet(Sets.intersect(set1, set2));
		io.printer.printSet(Sets.diff(set1, set2));
		io.printer.printSet(Sets.diff(set2, set1));
		io.printer.printSet(Sets.not(set1, n));
		io.printer.printSet(Sets.not(set2, n));
		io.close();
	}
}
