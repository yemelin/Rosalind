package simple;

import java.util.Iterator;
import java.util.LinkedList;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.RosaIO;
import rosaIO.Rstring;

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
		LinkedList<Fasta> fstl;
		if (args.length>0)
			fstl = FastaIO.fileToFastaList(RosaIO.DATAPATH+args[0]);
		else
			fstl= FastaIO.inputToFastaList();
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
			System.out.print(wrong.get(i).dna);
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
			System.out.println("->"+corr);
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
