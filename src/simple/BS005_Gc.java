package simple;

import rosaIO.Fasta;
import rosaIO.Rstring;
import rosaIO.Task;

public class BS005_Gc {
	public static void main(String[] args) {
		double gc, maxgc = 0.0f;
		int maxfst=0;

		Task io = new Task("gc", args);
		Fasta [] fsta = io.scanner.readFastaArray();
//	not extracting the following cycle to a method, because we need two
//	return values: max value and the index
		for (int i = 0; i < fsta.length; i++) {
			if ((gc = Rstring.countGC(fsta[i].dna))>maxgc) {
				maxfst = i;
				maxgc = gc;
			}
		}
		io.printer.println(maxgc);
		io.printer.println(fsta[maxfst].label);
		io.close();
	}
}
