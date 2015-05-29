package simple;

import java.util.HashMap;
import java.util.Map;

import rosaIO.Fasta;
import rosaIO.RosaPrinter;
import rosaIO.Rstring;
import rosaIO.Task;

public class BS010_Cons {
	
	@SuppressWarnings("serial")
	public static Map<Character, int[]> newProfileMap(int len) {
//	return anonymous class with instance initializer
		return new HashMap<Character, int[]>(){{
			for (int i=0; i<Rstring.DNALETTERS.length(); i++)
				put(Rstring.DNALETTERS.charAt(i), new int[len]);
		}};
	}
	
	public static Map<Character, int[]> buildProfile (Fasta[] fsta) {
		int len = fsta[0].dna.length();
		Map<Character, int[]> profile = newProfileMap(len);
		for (int i=0; i<fsta.length; i++)
			for (int j=0; j<len; j++)
				profile.get(fsta[i].dna.charAt(j))[j]++;
		return profile;
	}
	
	public static void printProfile (Map<Character, int[]> profile, RosaPrinter printer) {
		for (Map.Entry<Character, int[]> entry : profile.entrySet()) {
			printer.print(entry.getKey()+": ");
			printer.printArray(entry.getValue());
		}

	}
	public static String consensus (Map<Character, int[]> profile, int len) {
		char maxKey = 'A'; //not optimal, an extra knowledge for this function
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<len; i++) {
			for (Character key : profile.keySet()) {
				if (profile.get(key)[i]>profile.get(maxKey)[i])
					maxKey = key;
			}
			sb.append(maxKey);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Task io = new Task("cons", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		Map<Character, int[]> profile = buildProfile(fsta);
		io.printer.println(consensus(profile, fsta[0].dna.length()));
		printProfile(profile, io.printer);
	}

}
