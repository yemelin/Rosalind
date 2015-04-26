package util;

import java.util.Arrays;
// TODO: rewrite Condition nested class to allow it to be applied to other
// operations on set, for example search. diffOrCommon should be renamed then
public class Sets {
	public static int[] sorted (int a[]) {
		int ret[] = new int[a.length];
		System.arraycopy(a, 0, ret, 0, a.length);
		Arrays.sort(ret);
		return ret;
	}
	
	private abstract interface Condition {
		public boolean fitsCondition(int n);
	}
	private static class SearchForCommon implements Condition {
		@Override
		public boolean fitsCondition(int n) {
			return n>=0;
		}
	}
	private static class SearchForDiff implements Condition {
		@Override
		public boolean fitsCondition(int n) {
			return n<0;
		}
	}
	private static int[] diffOrCommon(int[] a, int[] b, Condition cond){
		int[] sa = sorted(b);
		int[] tmp = new int[a.length];
		int tmppos=0;
		for (int i=0; i<a.length; i++) {
			if (cond.fitsCondition(Arrays.binarySearch(sa, a[i]))) {
				tmp[tmppos++]=a[i];
			}
		}
		int[] ret = new int[tmppos];
		System.arraycopy(tmp, 0, ret, 0, tmppos);
		return ret;
	}
	
	public static void printSet(int[] a) {
		printSet(a, 0, a.length-1);
	}
	
	public static void printSet(int[] a, int start, int end) {
		if (end<a.length) {
			System.out.print("{");
			for (int i = start; i < end+1; i++) {
				System.out.print(a[i]);
				if (i<a.length-1)
					System.out.print(", ");
			}
			System.out.println("}");
		}
	}

//	----------------------------------------------------
//	U
	public static int[] universal (int n) {
		int[] ret = new int[n];
		for (int i = 0; i < ret.length; ret[i]=++i);
		return ret;
	}
//	not A = U-A
//	doesn't check whether all elements in a belong to U, but perhaps it should
	public static int[] not(int a[], int n) {
		return diff(universal(n), a);
	}
//	A or B
	public static int[] union(int a[], int b[]) {
		int[] diffa = diff(b,a);  
		int[] ret = new int[a.length+diffa.length];
		System.arraycopy(a, 0, ret, 0, a.length);
		System.arraycopy(diffa, 0, ret, a.length, diffa.length);
		return ret;
	}
//	A and B
	public static int[] intersect(int a[], int b[]) {
		return diffOrCommon(a, b, new SearchForCommon());
	}
// A - B
	public static int[] diff(int a[], int b[]) {
		return diffOrCommon(a, b, new SearchForDiff());
	}
}
