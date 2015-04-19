package simple;
// http://rosalind.info/problems/sign/
// TODO: make universal (with BigInteger) (may be skipped locally for this 
// task (condition is n<=6), but may be useful later)
// TODO: move useful functions to libraries
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import util.InOut;
import util.MathStats;

public class BS029_Sign {
	private static int smallFact(int n) {
		int ret=0;
		if (n>=0)
			ret = 1;
		for (int i=1; i<=n;i++)
			ret*=i;
		return ret;
	}
	
	public static int[] numToPerm(int n, int permSz) {
//		int permSz = alph.length;
		List <Integer> nums = new LinkedList <Integer>();
		for (int i=0; i<permSz; i++)
			nums.add(i+1);
		int a[] = new int[permSz];
		int div = smallFact(permSz-1);
		for (int i=permSz-1; i>=0; i--) {
			a[i] = nums.remove(n/div);
			n = n%div;
			if (i!=0) div/=i;			
//			System.out.printf("%d ", a[i]);
		}
//		System.out.println();
		return a;
	}
//	TODO: change from hard-coded 6 to handle arbitrary permutation's length
	public static int[] numToDigs (int n, int radix) {
		int [] a = new int [6];
		int i=0;
		do {
			a[i] = n%radix;
			n = n/radix;
//			System.out.printf("%d ", a[i]);
		} while (++i<6);
//		System.out.println();
		return a;
	}
	
	
	public static void addSigns (int a[], int signs[]) {
		for (int i=0; i<a.length; i++) {
			if (signs[i]==0)
				a[i] = Math.abs(a[i])*-1;
			else
				a[i] = Math.abs(a[i]);
		}
	}
	public static int pow2(int n) {
		return 1<<n;
	}
	
	public static void printArray(int a[]){
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i<a.length-1)
				System.out.print(" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int a[] = InOut.readInt(1);
		BigInteger perms = MathStats.factFall(0, a[0]);
		BigInteger signs = BigInteger.valueOf(2).pow(a[0]);
		if (a!=null) {
			System.out.println(perms
				.multiply(signs));
		}
//		for (BigInteger i= BigInteger.ZERO; i.compareTo(perms)<0; i.add(BigInteger.ONE) ) {
//			
//		}
//		for (int i=0; i<smallFact(a[0]); i++)
//			numToPerm(i, a[0]);
//		for (int i=0; i<8; i++)
//			numToDigs(i, 2);
		for (int i=0; i<smallFact(a[0]); i++) {
			int [] p = numToPerm(i, a[0]);
//			printArray(p);
//			System.out.println("----------------");
			for (int j=0; j<pow2(a[0]); j++) {
				addSigns(p, numToDigs(j, 2));
				printArray(p);
//				System.out.print(" ::: ");
//				printArray(numToDigs(j, 2));
//				System.out.println();
			}
		}
	}
}
