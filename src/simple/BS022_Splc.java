package simple;
//http://rosalind.info/problems/splc/
import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.Rstring;
//TODO: split removing introns and protein translation
public class BS022_Splc {
//	Remove all introns one by one. Based on the assumption that the solution is
//	unique. Introns are temporarily replaced by a dummy 'Z' to avoid
//	removing introns from the spliced dna (the may appear after splicing)
	public static String removeIntrons (Fasta fsta[]) {
		String s = fsta[0].dna;
		for (int i=1; i<fsta.length; i++) {
			s = s.replace(fsta[i].dna, "Z");
		}
		return (Rstring.rnaToProtein(s.replace("Z", "").replace('T', 'U')));
	}
	public static void main(String[] args) {
		Fasta fsta[] = FastaIO.inputToFastaArray();
		System.out.println(removeIntrons(fsta));
	}
}
