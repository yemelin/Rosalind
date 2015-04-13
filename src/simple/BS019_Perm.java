/* http://rosalind.info/problems/perm/
 * Task 19
 */
package simple;
import java.util.Scanner;

import util.MathStats;
public class BS019_Perm {
	public static void swap(int a[], int m, int k) {
		int d = a[m];
		a[m] = a[k];
		a[k] = d;
	}
	public static int[] range (int m, int n) {
		int a[] = new int[n-m+1]; 
		for (int i = m; i <= a.length; i++) {
			a[i-m] = i;
		}
		return a;
	}
	public static void printArray(int a[]){
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i<a.length-1)
				System.out.print(" ");
		}
		System.out.println();
	}
// recursive, displays permutations of a[n] as permutations of a[1:n]
//	for each of a[n]'s members swapped to be a[0]
	public static void printPermutations(int a[], int m){
		if (m==a.length)
			printArray(a);
		else {
			for (int i = m; i < a.length; i++) {
				swap(a,m,i);
				printPermutations(a, m+1);
				swap(a,m,i);
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(MathStats.factFall(0,n));
		printPermutations(range(1,n), 0);
		sc.close();
	}
}
