package bs048_Motz;

import rosaIO.Fasta;
import rosaIO.Task;

public class BS048_Motz {
	public static void main(String[] args) {
		Task io = new Task("motz", args);
		Fasta [] fsta = io.scanner.readFastaArray();
		String s = fsta[0].dna;
		io.printer.println(Motzkin.motzkinDnaBig(s));
		io.close();
	}
}
