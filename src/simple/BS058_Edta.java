package simple;
// TODO: cleanup, refactor, extract all useful to libs  
import java.util.ArrayList;
import java.util.List;

import rosaIO.Fasta;
import rosaIO.Rstring;
import rosaIO.Task;
import util.RosaArrays;

public class BS058_Edta {
	
	public static final int INS = 1;
	public static final int SUBST = 2;
	public static final int DEL = 3;
	
	public static List<Integer> traceBack (int[][] table) {
		List<Integer> ret = new ArrayList<>();
		int edit = 0;
		int i,j; 
		for (i=table.length-1, j=table[0].length-1; table[i][j]!=0;) {
			System.out.println(i+" "+j);
			if (table[i][j]>table[i-1][j]) {
				i--;
				edit = DEL;
			}
			else if (table[i][j]>table[i][j-1]) {
				edit = INS;
				j--;
			}
			else if (table[i][j]>table[i-1][j-1]) {
				edit = SUBST;
				i--; j--;
			}
			else {
				edit = 0;
				i--; j--;
			}
			ret.add(edit);
		}
		while (--i>=0)
			ret.add(0);
		return ret;
	}
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
//		String s1 = io.scanner.readLine();
//		String s2 = io.scanner.readLine();
		io.printer.println(RosaArrays.editDistance(fsta[0].dna, fsta[1].dna));
//		for (int[] a : RosaArrays.tbl) {
//			io.printer.printArray(a);
//		}
		List <Integer> tb = traceBack(RosaArrays.tbl);
//		for (Integer edit :  traceBack(RosaArrays.tbl))
//				System.out.print(edit+" ");
//		System.out.println();
		String[] ret = align(fsta[0].dna, fsta[1].dna, tb);
		io.printer.println(ret[0]);
		io.printer.println(ret[1]);
//		System.out.println(Rstring.hamming(ret[0], ret[1]));
	}

}
