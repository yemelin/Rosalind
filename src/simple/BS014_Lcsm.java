package simple;

import rosaIO.Fasta;
import rosaIO.Task;

public class BS014_Lcsm {

	public static void main(String[] args) {
		Task io = new Task ("lcsm", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		String[] dnas = collectDnasFromFastaArray(fsta);
		shortestToTheTop(dnas);
		io.printer.println(longestCommonSubstring(dnas));
		io.close();
	}

	private static String longestCommonSubstring(String[] dnas) {
		int k;
		for (int len = dnas[0].length(); len>0; len--) {
			for (int start=0; start<=dnas[0].length()-len; start++) {
				String pattern = dnas[0].substring(start, start+len);
				for (k=1; k<dnas.length; k++) {
					if (dnas[k].indexOf(pattern)==-1)
						break;
				}
				if (k==dnas.length)
					return pattern;
			}
		}
		return "";
	}

	private static void shortestToTheTop(String[] dnas) {
		if (dnas.length==0)
			return;
		int min = 0;
		for (int i = 0; i < dnas.length; i++) {
			if (dnas[i].length()<dnas[min].length())
				min = i;
		}
//	swap the shortest with the 0th
		String tmp = dnas[min];
		dnas[min] = dnas[0];
		dnas[0] = tmp;
	}

	private static String[] collectDnasFromFastaArray(Fasta[] fsta) {
		String [] ret = new String[fsta.length];
		for (int i = 0; i < ret.length; i++)
			ret[i] = fsta[i].dna;
		return ret;
	}

}
