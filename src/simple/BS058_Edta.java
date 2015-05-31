package simple;
// TODO: cleanup, refactor, extract all useful to libs  
import java.util.List;

import rosaIO.Fasta;
import rosaIO.Task;
import util.RosaArrays;

public class BS058_Edta {
		
	public static String[] align (String s1, String s2, List<Integer> edits) {
		StringBuffer st1 = new StringBuffer();
		StringBuffer st2 = new StringBuffer();
		int pos1=0, pos2=0;
		for (int i=edits.size()-1; i>=0; i--) {
			int edit = edits.get(i);
			if (edit == 3) {
				st1.append(s1.charAt(pos1++));
				st2.append("-");
			}
			else if (edit == 1) {
				st2.append(s2.charAt(pos2++));
				st1.append("-");
			}
			else {
				st1.append(s1.charAt(pos1++));
				st2.append(s2.charAt(pos2++));
			}
		}
		String[] ret = {st1.toString(), st2.toString()};
		return ret;
	}
	public static void main(String[] args) {
		Task io = new Task("edta", args);
		Fasta[] fsta = io.scanner.readFastaArray();

		io.printer.println(RosaArrays.editDistance(fsta[0].dna, fsta[1].dna));
		List <Integer> tb = RosaArrays.traceBack(RosaArrays.tbl);
		String[] ret = align(fsta[0].dna, fsta[1].dna, tb);
		
		io.printer.println(ret[0]);
		io.printer.println(ret[1]);
		io.close();
	}

}
