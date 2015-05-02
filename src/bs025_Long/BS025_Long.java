package bs025_Long;
import java.util.ArrayList;

import rosaIO.Fasta;
import rosaIO.Task;
public class BS025_Long {
	public static void main(String[] args) {
		Task io = new Task("long", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		ArrayList <String>ls = new ArrayList<>();		
		for (rosaIO.Fasta f : fsta)
			ls.add(f.dna);
		
		io.printer.println(Strings.merge(ls));
		io.close();
	}
}
