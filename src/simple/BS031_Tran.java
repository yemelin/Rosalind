package simple;

// http://rosalind.info/problems/tree/
import rosaIO.Fasta;
import rosaIO.Task;

public class BS031_Tran {
	public static double transRatio(String s1, String s2) {
		int len = s1.length();
		int numTransition = 0;
		int numTransversion = 0;
		if (len!=s2.length())
			return -1.0;
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i)!=s2.charAt(i)) {
				if (s2.charAt(i)==nucleoTransition(s1.charAt(i)))
					numTransition++;
				else 
					numTransversion++;
			}
		}
		if (len == numTransition)
			len++; //return length, if we have transitions only
		return (double)numTransition/(numTransversion);
	}
	
	private static char nucleoTransition(char c) {
		char ret;
		switch (c) {
			case 'A': ret = 'G'; break;
			case 'C': ret = 'T'; break;
			case 'T': ret = 'C'; break;
			case 'G': ret = 'A'; break;
			default: ret = c;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Task io = new Task("sseq", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		if (fsta.length==2)
			io.printer.println(transRatio(fsta[0].dna, fsta[1].dna));
		else
			System.err.println("Input corrupted");
		io.close();
	}
}
