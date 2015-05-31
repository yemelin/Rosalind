// http://rosalind.info/problems/sign/
package simple;
// There is n! permutations and 2^n ways to add signs
// Printing is done non-recursive, as follows: as long as the permutations can be
// counted, we can assume each permutation to have a unique number
// the same can be done with all the ways to add signs

// TODO: make universal (with BigInteger) (may be skipped locally for this 
// task (condition is n<=6), but may be useful later)
// TODO: move useful functions to libraries
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import rosaIO.Task;
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

// decode number to the permutation	
	public static int[] numToPerm(int n, int permSz) {
		List <Integer> nums = new LinkedList <Integer>();
		for (int i=0; i<permSz; i++)
			nums.add(i+1);
		int a[] = new int[permSz];
		int div = smallFact(permSz-1);
		for (int i=permSz-1; i>=0; i--) {
			a[i] = nums.remove(n/div);
			n = n%div;
			if (i!=0) div/=i;			
		}
		return a;
	}

//decode the number 0 - (2^n-1) to the corresponding signs positions.
//it is a normal "split number to digits" function. For signs we need
//radix=2, which will result in a sequence of 0(-) and 1(+)
	public static int[] numToDigs (int n, int radix, int permSz) {
		int [] a = new int [permSz];
		int i=0;
		do {
			a[i] = n%radix;
			n = n/radix;
		} while (++i<permSz);
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
	
//	public static void printArray(int a[]){
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i]);
//			if (i<a.length-1)
//				System.out.print(" ");
//		}
//		System.out.println();
//	}

	public static void main(String[] args) {
		Task io = new Task("sign", args);
		int n = io.scanner.readInt();
		BigInteger perms = MathStats.factFall(0, n);
		BigInteger signs = BigInteger.valueOf(2).pow(n);
		io.printer.println(perms.multiply(signs));

		for (int i=0; i<smallFact(n); i++) {
			int [] p = numToPerm(i, n);
			for (int j=0; j<pow2(n); j++) {
				addSigns(p, numToDigs(j, 2, n));
				io.printer.printArray(p);
			}
		}
	}
}
