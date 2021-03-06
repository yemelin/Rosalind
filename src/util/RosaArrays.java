package util;

import java.util.ArrayList;
import java.util.List;


public class RosaArrays {
	public static int max(int a, int b) {
		return (a>b)? a:b;					
	}
	public static void swap (int[] a, int i, int j) {
		int c = a[i];
		a[i] = a[j];
		a[j] = c;		 
	}
	private static void reverse(int[] a) {
		for (int i=0; i<a.length/2; i++)
			swap(a, i, a.length-i-1); 
	}
	
//	Wagner-Fischer
//The global var tbl is a temporary hack. Table is a result of editDistance
//work. Either editDistance should return the table, and distance will be
//simply it's left lower corner value, or an Object holding
//both a table and a distance
	public static int[][] tbl; //TODO: get rid of this global var!!!
	public static int editDistance (String s1, String s2) {
//		System.out.println("Edit distance between "+s1);
//		System.out.println(s2);
		int table[][] = new int [s1.length()+1][s2.length()+1];
		for (int i = 0; i <= s1.length(); i++)
			table[i][0] = i;
		for (int j = 0; j <= s2.length(); j++) 
			table[0][j]=j;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				table[i][j]=table[i-1][j-1];
				if (s1.charAt(i-1)!=s2.charAt(j-1)) {//look for min previous
					if	(table[i][j-1] < table[i][j])
						table[i][j] = table[i][j-1]; //ins
					if (table[i-1][j]< table[i][j])
						table[i][j] = table[i-1][j]; //del
													//i-1,j-1 is min -> subst
					table[i][j]++;	//+1 edit operation
				}
			}
		}
		tbl = table;//DEBUG
		return table[s1.length()][s2.length()];
	}
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
//-----------------------------------
//	longest common subsequence
//	TODO: generalize it. Array should be passed as a plain object and
//	there should be some comparator class, or some adapter classes should be
//	passed instead of arrays
	public static int[] lcs (int a[], int b[]) {
		int table[][] = new int[a.length+1][b.length+1];
		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j<=b.length; j++) {
				if (a[i-1]==b[j-1]) {
					table[i][j] = table[i-1][j-1]+1;
				}
				else
					table[i][j] = max(table[i-1][j], table[i][j-1]);
			}
		}
//	backtracking the longest subsequence
		int ret[] = new int[ table[a.length][b.length] ];
		for (int i=a.length, j=b.length, k = ret.length-1; i!=0 && j!=0;) {
			if (table[i][j]==table[i-1][j])
				i--;
			else if (table[i][j]==table[i][j-1])
				j--;
			else {
				ret[k--]=a[i-1];
				i--; j--;
			}
		}
		return ret;
	}
	
	public static String lcs (String a, String b) {
		int table[][] = new int[a.length()+1][b.length()+1];
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j<=b.length(); j++) {
				if (a.charAt(i-1)==b.charAt(j-1)) {
					table[i][j] = table[i-1][j-1]+1;
				}
				else
					table[i][j] = max(table[i-1][j], table[i][j-1]);
			}
		}
//	backtracking the longest subsequence
		char ret[] = new char[ table[a.length()][b.length()] ];
		for (int i=a.length(), j=b.length(), k = ret.length-1; i!=0 && j!=0;) {
			if (table[i][j]==table[i-1][j])
				i--;
			else if (table[i][j]==table[i][j-1])
				j--;
			else {
				ret[k--]=a.charAt(i-1);
				i--; j--;
			}
		}
		return new String(ret);
	}
	
	
////	TODO: add backtracking the subsequence
//	public static Object[] lcs (Object a[], Object b[]) {
//		int table[][] = new int[a.length+1][b.length+1];
//		for (int i = 1; i <= a.length; i++) {
//			for (int j = 1; j<=b.length; j++) {
//				if (a[i-1].equals(b[j-1])) {
//					table[i][j] = table[i-1][j-1]+1;
//				}
//				else
//					table[i][j] = max(table[i-1][j], table[i][j-1]);
//			}
//		}
////		change length of the output array
//		Object ret2[] = new Object[ table[a.length][b.length] ];
//		return ret2;
//	}
	
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
