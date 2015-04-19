package bs033_Cat;

import rosaIO.FastaIO;
import rosaIO.RosaIO;

public class BS033_Cat {
	public static void main(String[] args) {
//		for (int i=0; i<10; i++) {
//			System.out.println(catalanNonRec(i));
//		}
//		String s = "UAGCGUGAUCAC";
		rosaIO.Fasta [] fsta = FastaIO.fileToFastaArray(RosaIO.DATAPATH+args[0]);
//		String s = "AAUGCUGAUUAUCGGCACGCGCGUGCACCUAUUAAUUGCAAUACCGGCGUUAAUACGUAUAUAUGCACCGCGAUAUUAUAUGCAUAGGUACGGCGCCUAG";
//		String s = "GAUUAUCGGCACGCGCGUGCACCUAUUAAUUGCAAUACCGGCGUUAAUACGUAUAUAUGCACCGCGAUAUUAUAUGCAUAGGUACGGCGCCUAG";
//		String s ="GCGCGUGCACCUAUUAAUUGCAAUACCGGCGUUAAUACGUAUAUAUGCACCGCGAUAUUAUAUGCAUAGGUACGGCGCCUAG";
//		String s = "GUGCACCUAUUAAUUGCAAUACCGGCGUUAAUACGUAUAUAUGCACCGCGAUAUUAUAUGCAUAGGUACGGCGCCUAG";
		String s = fsta[0].dna;
//		System.out.println(s);
//		for (int i=0; i<10; i++) {
			System.out.println(Catalan.catalanDna(s));
			System.out.println(Catalan.catalanDnaBig(s));
//			System.out.println(catalanDnaRec(s, 0, s.length()-1));
//			s = s+"aa";
//		}

	}

}
