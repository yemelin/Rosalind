package simple;

import java.util.LinkedList;
import rosaIO.Fasta;
import rosaIO.Rstring;
import rosaIO.Task;

//TODO: clarify the code
//TODO: check if LinkedLIst is really needed or List<> is enough, change back
//readFastaList type to simple List<>, if needed
//TODO: use library's hamming
public class BS034_Corr {
	private static boolean removeAllNextCopies(LinkedList<Fasta> lf, int n ) {
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
		LinkedList<Fasta> fstl = io.scanner.readFastaList();

		LinkedList<Fasta> wrong = new LinkedList<>();
		Fasta fst;
		int i=0;
		while (i<fstl.size()) {
			if (!removeAllNextCopies(fstl, i)) {
				wrong.add(fst=fstl.remove(i));
//				System.out.println(fst.dna+" "+Rstring.dnaReverseComplement(fst.dna));
			}
			else i++;
		}
		String corr = "none found";
		for (i=0; i<wrong.size(); i++) {
			io.printer.print(wrong.get(i).dna);
			for (int j=0; j<fstl.size(); j++)
				if (hamming(wrong.get(i).dna, fstl.get(j).dna )==1) {
					corr = fstl.get(j).dna;
					break;
				}
				else if (hamming(wrong.get(i).dna, 
						Rstring.dnaReverseComplement(fstl.get(j).dna) )==1) {
					corr = Rstring.dnaReverseComplement(fstl.get(j).dna);
					break;
				}
			io.printer.println("->"+corr);
		}
	}

	public static int hamming (String s1, String s2) {
		int ret=0;
		if (s1.length()!=s2.length())
			ret = -1;
		else
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i)!=s2.charAt(i))
					ret++;
			}
		return ret;
	}
}
