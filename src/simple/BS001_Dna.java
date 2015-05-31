//http://rosalind.info/problems/dna/
package simple;

import rosaIO.Task;
import util.Rstring;

public class BS001_Dna {
	private final static char alpha[] = Rstring.DNALETTERS.toCharArray(); //sorted
	
//	Training. util.Arrays has a binary search method
	private static int binsearch (char c, char a[]) {
		int lo=0, hi = a.length-1, mid=0;
		while (hi>=lo) {
			mid = (hi+lo)/2;
			if (c>a[mid])
				lo = mid+1;
			else if (c<a[mid])
				hi = mid-1;
			else break;				
		}
		if (a[mid]!=c)
			mid = -mid-1;
		return mid;
	}

//Return array of number of appearances of chars from alpha in s
//Using array	
	private static int[] countChars (String s, char alpha[]) {
		int ret[] = new int[alpha.length];
		int pos;
		for (int i = 0; i < s.length(); i++) {
//just for fun. No profit from binary search, as ptrn too short
			if ((pos = binsearch(s.charAt(i), alpha))>=0)
				ret[pos]++;
		}
		return ret;
	}
	

public static void main(String[] args) {
		Task io = new Task ("dna", args);
		String s = io.scanner.readLine();
		io.printer.printArray(countChars(s,alpha));
//		io.printer.printArray(Rstring.countChars(s, Rstring.DNALETTERS));//is OK
		io.close();
	}
}
