package simple;

import java.util.Scanner;

//http://rosalind.info/problems/lexv/
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
	
	public static void printAllWordsNonRec (String alph, int maxlen) {
		int len=0;
		StringBuffer sb = new StringBuffer();
		sb.setLength(maxlen);
		do {
			if (len<maxlen) {
				sb.setCharAt(len++, alph.charAt(0));
			}
			else {
				while (len>0 && 			
					sb.charAt(len-1)==alph.charAt(alph.length()-1))
					len--;
				if (len>0 ) {
					sb.setCharAt(len-1, nextChar(alph, sb.charAt(len-1)));
				}
			}
			System.out.println(sb.substring(0,len));
		} while (len>0);
	}
//TODO: check for invalid input
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		String alph = sc.nextLine().replaceAll("\\s", "");
		int n = sc.nextInt();
		sb.setLength(n);
		printAllWordsNonRec(alph, n);

	}

}
