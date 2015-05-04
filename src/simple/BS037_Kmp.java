package simple;

import rosaIO.Fasta;
import rosaIO.Task;

public class BS037_Kmp {
	public static int[] prefixFunction (String s) {
		int[] p = new int[s.length()+1];
		int k;
		p[0]=-1; p[1]=0;
		for (int i=2; i<s.length(); i++) {
			k = p[i-1];
//	Important cycle: we take the "border" (check this term!) and try to append one letter. 
//	If the letters	after the prefix and the current suffix of the substring are 
//  different, then we try to take a shorter border, which will be the maximum 
//	border of the border taken at the current repetition. And so on, until we 
//	find it or reach zero
			while (k>0 && (s.charAt(k)!=s.charAt(i-1)))//0-based string numbering
				k=p[k];
			if (s.charAt(i-1)==s.charAt(k)) //border found
				k++;
			p[i]=k;
		}
		return p;
	}

	public static void main(String[] args) {
		Task io = new Task("kmp", args);
		Fasta [] fsta = io.scanner.readFastaArray();
		io.printer.printArray(prefixFunction(fsta[0].dna), 1);
		io.close();
	}

}
