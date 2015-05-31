//http://rosalind.info/problems/grph/
package simple;
import rosaIO.Fasta;
import rosaIO.Task;

public class BS012_Grph {

	private static boolean overlap(String head, String tail) {
		return head.substring(0,3)
				.equals(tail.substring(tail.length()-3));
	}
	public static void main(String[] args) {
		Task io = new Task("grph",args);
		Fasta[] fsta = io.scanner.readFastaArray();
		for (int i=0; i<fsta.length; i++)
			for (int j = 0; j < fsta.length; j++)
				if (i!=j && overlap(fsta[j].dna, fsta[i].dna))
					io.printer.println(fsta[j].label+" "+fsta[i].label);
		io.close();
	}
}
