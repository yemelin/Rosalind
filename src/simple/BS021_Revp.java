package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.RosaIO;
import rosaIO.Rstring;

public class BS021_Revp {
	public static void getAllPalindromes (String dna) {
//		String rp_dna = (new StringBuffer(dna)).reverse().toString();
		String rp_dna = Rstring.dnaReverseComplement(dna);
//		System.out.println(rp_dna+" "+dna.length());
//		for (int i=0; i<dna.length()-4; i++)
//			for (int j=4;i+j<=dna.length()&&j<i+12;j++) {
				
		for (int j=4;j<13;j++)
			for (int i=0; i<dna.length()-j+1; i++) {
//				System.out.print(i+" "+j+" "+ dna.substring(i, i+j)+" ");
//				System.out.println(rp_dna.substring(dna.length()-i-j, dna.length()-i));
				if (dna.regionMatches(i, rp_dna, dna.length()-i-j, j))
					System.out.println((i+1)+" "+j);
			}
	}
	public static void main(String[] args) {
//		TODO: check input for errors
			Fasta fst[];
			if (args.length>0)
				fst = FastaIO.fileToFastaArray(RosaIO.DATAPATH+args[0]);
			else
				fst= FastaIO.inputToFastaArray();
			getAllPalindromes(fst[0].dna);
	}
}
