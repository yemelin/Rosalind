package bs025_Long;
import java.util.ArrayList;

import rosaIO.FastaIO;
import rosaIO.RosaIO;
public class BS025_Long {
	public static void main(String[] args) {
		rosaIO.Fasta fst [];
		if (args.length>0)
			fst = FastaIO.fileToFastaArray(RosaIO.DATAPATH+args[0]);
		else
			fst = FastaIO.inputToFastaArray();
		ArrayList <String>ls = new ArrayList<>();
		
		for (rosaIO.Fasta f : fst) {
			ls.add(f.dna);
//			System.out.println(f.dna);
		}
		System.out.println(Strings.merge(ls));
	}
}
