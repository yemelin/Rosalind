package simple;

import rosaIO.Fasta;
import rosaIO.RosaPrinter;
import rosaIO.Rstring;
import rosaIO.Task;

public class BS021_Revp {
	private static final int MIN = 4;  //min palindrome length
	private static final int MAX = 12; //max palindrome length
	public static void printAllPalindromes (String dna, RosaPrinter rp) {
//Get the reverse complement of the string to avoid reversing all the substrings
		String rp_dna = Rstring.dnaReverseComplement(dna);
		for (int j=MIN;j<MAX+1;j++)
			for (int i=0; i<dna.length()-j+1; i++) {
				if (dna.regionMatches(i, rp_dna, dna.length()-i-j, j))
					rp.println((i+1)+" "+j);
			}
	}
	public static void main(String[] args) {
			Task io = new Task ("revp", args);
			Fasta[] fsta = io.scanner.readFastaArray();
			printAllPalindromes(fsta[0].dna, io.printer);
			io.close();
	}
}
