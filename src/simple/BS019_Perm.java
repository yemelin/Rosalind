// http://rosalind.info/problems/perm/
 
package simple;
//TODO: swap() for int array should definitely be in some library
import rosaIO.RosaPrinter;
import rosaIO.Task;
import util.MathStats;
public class BS019_Perm {
	private static void swap(int a[], int m, int k) {
		int d = a[m];
		a[m] = a[k];
		a[k] = d;
	}
	
//	returns an array filled with ints from m to n
	private static int[] range (int m, int n) {
		int a[] = new int[n-m+1]; 
		for (int i = m; i <= a.length; i++) {
			a[i-m] = i;
		}
		return a;
	}
// recursive, displays permutations of a[n] as permutations of a[1:n]
//	for each of a[n]'s members swapped to be a[0]
	public static void printPermutations(int a[], int m, RosaPrinter ps){
		if (m==a.length)
			ps.printArray(a);
		else {
			for (int i = m; i < a.length; i++) {
				swap(a,m,i);
				printPermutations(a, m+1, ps);
				swap(a,m,i);
			}
		}
	}
	public static void main(String[] args) {
		Task io = new Task("perm", args);
		int n = io.scanner.readInt();
		io.printer.println(MathStats.factFall(0,n));
		printPermutations(range(1,n), 0, io.printer);
		io.close();
	}
}
