//http://rosalind.info/problems/corr/
package simple;

import java.util.LinkedList;
import java.util.List;

import rosaIO.Fasta;
import rosaIO.Task;
import util.Rstring;

//TODO: clarify the code
public class BS034_Corr {
//	remove all the copies of the element (including reverse complement)
//	that are after it (number>element_number). Returns
//	true if there was any removal
	private static boolean removeAllNextCopies(List<Fasta> lf, int n ) {
		int sz = lf.size();
		String dna = lf.get(n).dna;
		for (int i=sz-1; i>n; i--) {
			if (lf.get(i).dna.equals(dna) ||
					lf.get(i).dna.equals(Rstring.dnaReverseComplement(dna)))
				lf.remove(i);
		}
		return sz!=lf.size();
	}

	public static void main(String[] args) {
		Task io = new Task("sseq", args);
		List<Fasta> fstl = io.scanner.readFastaList();

		LinkedList<Fasta> wrong = new LinkedList<>();
		int i=0;
//	find all wrong records and move them to wrong list
		while (i<fstl.size()) {
			if (!removeAllNextCopies(fstl, i)) {
				wrong.add(fstl.remove(i));
			}
			else i++;
		}
		String corr = "none found";
// find the corresponding correct record for each wrong
		for (i=0; i<wrong.size(); i++) {
			io.printer.print(wrong.get(i).dna);
			for (int j=0; j<fstl.size(); j++)
				if (Rstring.hamming(wrong.get(i).dna, fstl.get(j).dna )==1) {
					corr = fstl.get(j).dna;
					break;
				}
				else if (Rstring.hamming(wrong.get(i).dna, 
						Rstring.dnaReverseComplement(fstl.get(j).dna) )==1) {
					corr = Rstring.dnaReverseComplement(fstl.get(j).dna);
					break;
				}
			io.printer.println("->"+corr);
		}
	}
}
