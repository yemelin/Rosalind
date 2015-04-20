package util;

public class RosaArrays {
	public static int max(int a, int b) {
		return (a>b)? a:b;					
	}
	private static void swap (int[] a, int i, int j) {
		int c = a[i];
		a[i] = a[j];
		a[j] = c;		 
	}
	private static void reverse(int[] a) {
		for (int i=0; i<a.length/2; i++)
			swap(a, i, a.length-i-1); 
	}
	
	public static void printArray(int a[], int start, int end) {
		if (end<a.length) {
			for (int i = start; i < end+1; i++) {
				System.out.print(a[i]);
				if (i<a.length-1)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static void printArray(int a[], int start) {
		printArray(a,start,a.length-1);
	}
	public static void printArray(int a[]) {
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i]);
//			if (i<a.length-1)
//				System.out.print(" ");
//		}
//		System.out.println();
		printArray(a,0,a.length-1);
	}

//	longest common subsequence
	public static int[] lcs (int a[], int b[]) {
		int table[][] = new int[a.length+1][b.length+1];
		int ret[] = new int[a.length];
		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j<=b.length; j++) {
				if (a[i-1]==b[j-1]) {
					table[i][j] = table[i-1][j-1]+1;
					ret[ table[i][j]-1 ] = a[i-1];
				}
				else
					table[i][j] = max(table[i-1][j], table[i][j-1]);
			}
		}
//	change length of the output array
		int ret2[] = new int[ table[a.length][b.length] ];
		System.arraycopy(ret, 0, ret2, 0, ret2.length);
		return ret2;
	}
	
	@Deprecated
//	uses the fact that arrays longest increasing subsequence is its common subsequence
//	with own sorted copy, but the complexity of this algorithm is O(m*n)
	public static int[] longestSeq2 (int[] a, boolean inc) {
		int b[] = new int[a.length];
		System.arraycopy(a, 0, b, 0, a.length);
		java.util.Arrays.sort(b);
//		System.out.println(java.util.Arrays.binarySearch(b, 6));
		if (!inc)
			reverse(b);
		return lcs(a,b);
	}
	
//	"fast version", O(n*log(n)) 
	public static int[] longestSeq(int[] a, boolean inc) {
		int prevs[] = new int[a.length];
		int tails[] = new int [a.length+1];
		int maxL=0;
		for (int i=0; i<a.length; i++) {
//	binary search. Usage of util.Arrays.binsearch is complicated by its messy
//	negative return value. Bigger than the biggest and less than the biggest 
//	give the same result: -(upper bound)-1. Thus I use my own binsearch.
			int lo =1, hi = maxL, mid;
			while (lo<=hi) {
				mid = (lo+hi+1)/2;
				if (a[tails[mid]]<a[i] && inc ||	//tails increasing
						a[tails[mid]]>a[i] && !inc) //tails decreasing
				if (a[tails[mid]]<a[i])
					lo = mid+1;
				else
					hi = mid-1;
			}
			prevs[i] = tails[lo-1];
			tails[lo] = i;
			if (lo>maxL)
				maxL = lo;
		}
		int ret[] = new int[maxL];
		int k = tails[maxL];
		for (int i = ret.length-1; i>=0; i--) {
			ret[i] = a[k];
			k = prevs[k];
		}
		return ret;
	}
}
