//http://rosalind.info/problems/lexf/
package simple;

import rosaIO.RosaPrinter;
import rosaIO.Task;

public class BS023_Lexf {
	private static StringBuffer pref = new StringBuffer();
	private static String delimRegEx = "[^A-Z]";//"not a symbol"

//uses global pref, but recuirsion looks simpler to me with a global var
	public static void printPermutations(char a[], int n, RosaPrinter rp) {
		for (int i=0; i<a.length; i++) {
			pref.setCharAt(pref.length()-n, a[i]);
			if (n==1)
				rp.println(pref);
			else
				printPermutations(a, n-1, rp);
		}
	}
	public static void main(String[] args) {
		Task io = new Task("lexf", args);
		String st = io.scanner.readLine().replaceAll(delimRegEx, "");
		int wlen = io.scanner.readInt();
		pref.setLength(wlen);
		printPermutations(st.toCharArray(), wlen, io.printer);
		io.close();
	}

}
