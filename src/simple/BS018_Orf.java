package simple;
//http://rosalind.info/problems/orf/
import java.util.TreeSet;

import rosaIO.Fasta;
import rosaIO.Task;
import util.Rstring;
public class BS018_Orf {
	public static String[] findAllProts (String dna, TreeSet<String> set) {
		int start = 0;
		String nextprot;
		StringBuffer sb = new StringBuffer (dna.replace('T', 'U'));

		while ((start = sb.indexOf(Rstring.STARTCODON, start))!=-1) {			
			nextprot = Rstring.rnaToProtein(sb.substring(start));
//check if there is STOP in the end or just the end of the string
//STOP is not included to protein string, but the corresponding rna has it
			if (nextprot.length()==(sb.length()-start)/3)
				break;

			set.add(nextprot);
			start+=3;
		}
		return null;
	}
//TreeSet is used to add only distinct proteins
	public static String[] findAllProteins(String dna) {
		TreeSet<String> set = new TreeSet<String>();
		findAllProts (dna, set);
		findAllProts(Rstring.dnaReverseComplement(dna), set);
		return (String[]) set.toArray(new String[set.size()]);
	}

	public static void main(String[] args) {
		Task io = new Task("orf", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		for (String st : findAllProteins(fsta[0].dna))
			io.printer.println(st);
	}
}
