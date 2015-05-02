package simple;

import rosaIO.Task;
public class BS003_Revc {
	private static final String pattern = "ACGT";
	private static final char ptrn[] = {'A','C','G','T',};

//using Arrays
	public static int[] countCharsa(char s[]) {
		int i;
		int ret[] = new int[ptrn.length];
		for (char b : s) {
//just for fun. No profit from binary search, as ptrn too short
			if ((i=java.util.Arrays.binarySearch(ptrn, b))>=0)
				ret[i]++;
		}
		return ret;
	}
//using String	
	public static int[] countChars(String s){
		int ret[] = new int[pattern.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < pattern.length(); j++) {
				if (s.charAt(i)==pattern.charAt(j)) {
					ret[j]++;
					break;
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Task io = new Task ("revc", args);
		String st = io.scanner.readLine();
		io.printer.printArray(countChars(st));
		io.printer.printArray(countCharsa(st.toCharArray()));
		io.close();
	}
}
