//http://rosalind.info/problems/lexv/
package simple;

import rosaIO.RosaPrinter;
import rosaIO.Task;

//TODO: get rid of global vars, make input more universal and add to libs
public class BS039_Lexv {

		static StringBuffer sb = new StringBuffer("");
		static int depth = 0;

	public static void printAllWords (String alph, int maxlen) {
		depth++;
		for (int i=0; i<alph.length(); i++) {
			sb.setCharAt(depth-1, alph.charAt(i));
			System.out.println(sb.substring(0, depth));
			if (depth<maxlen)
				printAllWords(alph, maxlen);
		}
		depth--;
	}
//	temporary, fix later
	private static char nextChar(String alph, char c) {
		int i = alph.indexOf(c)+1;
		return alph.charAt(i);
	}
// print all words from alph, ordered lexicographically in respect to alph
// alph defines the order
	public static void printAllWordsNonRec (String alph, int maxlen, RosaPrinter rp) {
		int len=0;
		StringBuffer sb = new StringBuffer();
		sb.setLength(maxlen);
		do {
//	try to add "0" to the left
			if (len<maxlen) {
				sb.setCharAt(len++, alph.charAt(0));
			}
			else {
//	otherwise add 1 to the first digit from the right, which is not "9"
//	the rest is truncated by len when printing
				while (len>0 && 			
					sb.charAt(len-1)==alph.charAt(alph.length()-1))
					len--;
				if (len>0 ) {
					sb.setCharAt(len-1, nextChar(alph, sb.charAt(len-1)));
				}
			}
			rp.println(sb.substring(0,len));//len truncates
		} while (len>0);
	}
	
//TODO: check for invalid input
	public static void main(String[] args) {
		Task io = new Task("lexv", args);
		String alph = io.scanner.readLine().replaceAll("\\s", "");
		int n = io.scanner.readInt();
		sb.setLength(n);
		printAllWordsNonRec(alph, n, io.printer);

	}

}
