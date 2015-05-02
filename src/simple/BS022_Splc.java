package simple;
//http://rosalind.info/problems/splc/
import rosaIO.Fasta;
import rosaIO.Rstring;
import rosaIO.Task;
//TODO: split removing introns and protein translation
public class BS022_Splc {
//	Remove all introns one by one. Based on the assumption that the solution is
//	unique. Introns are temporarily replaced by a dummy 'Z' to avoid
//	removing introns from the spliced dna (they may appear after splicing)
	public static String removeIntrons (Fasta fsta[]) {
		String s = fsta[0].dna;
		for (int i=1; i<fsta.length; i++) {
			s = s.replace(fsta[i].dna, "Z");
		}
		return (Rstring.rnaToProtein(s.replace("Z", "").replace('T', 'U')));
	}
	public static void main(String[] args) {
		Task io = new Task("splc", args);
		Fasta fsta[] = io.scanner.readFastaArray();
		io.printer.println(removeIntrons(fsta));
		io.close();
	}
}
