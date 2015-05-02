package simple;

import rosaIO.Rstring;
import rosaIO.Task;

//http://rosalind.info/problems/mrna/

public class BS017_Mrna {
	private static String stops[] = Rstring.protLetterToRnaTriplets('Z');
//	TODO: consider using sextet.substring(1,4).indexOf(stop) or
//	sextet.indexOf(stop, 1)
	private static boolean noStop(String rna1, String rna2) {
		String sextet = rna1+rna2;
		for (String stop : stops) {
			if (sextet.substring(1, 3).equals(stop) ||
					sextet.substring(2, 4).equals(stop)) {
				return false;
			}
		}
		return true;
	}
	public static long mycount (String p) {
		String states[] = Rstring.protLetterToRnaTriplets(p.charAt(0));
		long counts[] = new long[states.length];
//	TODO: probably, use Arrays.fillArray() here
		for (int i=0; i<states.length;i++)
			counts[i]=1;
		String newstates[]=null;
		long newcounts[]=null;
		for (int i = 1; i<p.length(); i++) {
			newstates = Rstring.protLetterToRnaTriplets(p.charAt(i));
			if (newstates==null) break;
			newcounts = new long[newstates.length];
//TODO: remove new states to which no transition is allowed (everything resulting
//in stop codon appearing). Distinguish them from those, where 0 count is
//a result of modulo operation!
//Note 02.03.15 - perhaps, no need to, if noStop() handles all stops in a sextet
//TODO: optimize extensive modulo usage
			for (int j = 0; j<states.length; j++)
				for (int k = 0; k<newstates.length; k++) {
					if (noStop(states[j], newstates[k]))
						newcounts[k]= (newcounts[k]+counts[j])%1000000;
				}
			states = newstates;
			counts = newcounts;
		}
		long ret=0l;
		for (int i=0; i<newcounts.length; i++)
			ret+= newcounts[i];
		return (ret*3)%1000000;
	}
	public static void main(String[] args) {
		Task io = new Task("mrna",args);
		String s = io.scanner.readLine();
		io.printer.println(mycount(s));
		io.close();
	}
}
