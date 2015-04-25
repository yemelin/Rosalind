package bs048_Motz;

import rosaIO.FastaIO;

public class BS048_Motz {
	public static void main(String[] args) {
//		rosaIO.Fasta [] fsta = FastaIO.fileToFastaArray(RosaIO.DATAPATH+args[0]);
		rosaIO.Fasta [] fsta = FastaIO.obtainFastaArray(args, 1);
		String s = fsta[0].dna;
//			System.out.println(Catalan.catalanDna(s));
			System.out.println(Motzkin.motzkinDnaBig(s));
	}
}
