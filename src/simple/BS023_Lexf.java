package simple;
import java.util.List;

import rosaIO.RosaIO;

//TODO: input is just ugly
//TODO: reduce number of global vars
public class BS023_Lexf {
	private static final String DELIMS = " \t\n";
	private static char a[] = new char[10];
	private static boolean isDelim(char c) {
		return DELIMS.indexOf(c)!=-1;
	}
	static StringBuffer pref = new StringBuffer();
	static int sz=0, wlen=0;
	public static void printPermutations(char a[], int n) {
		for (int i=0; i<sz; i++) {
			pref.setCharAt(pref.length()-n, a[i]);
			if (n==1)
				System.out.println(pref);
			else
				printPermutations(a, n-1);
		}
	}
	public static void main(String[] args) {
//	input read as two strings: symbols and int(length of strings to be formed)
		List <String> s = RosaIO.getAllInputToList(args);
		int i=0;
//split 1st string to alphabeth
		while (sz<10 && i<s.get(0).length()) {
			if (!isDelim(s.get(0).charAt(i)))
				a[sz++]=s.get(0).charAt(i);
			i++;
		}
		
		try {
			wlen = Integer.parseInt(s.get(1));
		}
		catch (NumberFormatException e) {
			System.exit(1);
		}
		pref.setLength(wlen);

		printPermutations(a, wlen);
		
	}

}
