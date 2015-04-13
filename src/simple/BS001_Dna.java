package simple;

import rosaIO.RosaIO;

//http://rosalind.info/problems/dna/
public class BS001_Dna {
	private final static char alpha[] = {'A','C','G','T'}; //sorted 

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
			mid *=-1;
		return mid;
	}
	//Return array of number of appearances of chars from alpha in s 
	private static int[] countChars (String s, char alpha[]) {
		int ret[] = new int[alpha.length];
		for (int i = 0; i < s.length(); i++) {
			ret[binsearch(s.charAt(i), alpha)]++;
		}
		return ret;
	}
	public static void main(String[] args) {
		String s = RosaIO.getAllInput(args);
		if (s!=null)
			RosaIO.printArray(countChars(s,alpha));
	}
}
