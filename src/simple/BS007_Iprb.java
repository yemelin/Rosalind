//http://rosalind.info/problems/iprb/
package simple;

/*
k+m+l = n, k are homozygous dominant (AA), m - Aa, l - aa
P(D) - probabilty, that the child of two organism will have a dominant gene
A = n*(n-1) total number of pairs with order (e.g. lk and kl are 2 variants)
N(kk) = k*(k-1)		P(D,kk) = 1
N(km|mk) = 2k*m		P(D,mk) = 1
N(kl|lk) = 2k*l		P(D,mk) = 1
N(mm) = m*(m-1)		P(D,mk) = 3/4
N(ml|lm) = 2m*l		P(D,mk) = 1/2
N(ll) = l*(l-1)		P(D,mk) = 0

k(k-1)+2km+2kl = k(k+2m+2*l-1) = k(2n-k-1)= 
P(D) = [P(kk) + P(km) + P(kl)]*1 + P(mm)*3/4 + P(ml|lm)*1/2 + P(ll)*0
P(D) = (k(2n-k-1)/A)*1 + (m*(m-1)/A)*0.75 + 2*(m*l/A)*1/2 
P(D) = k(2n-k-1)/A + (m*(m-1)/A)*0.75 + m*l/A
P(D) = (k*(2n-k-1)+m*(m-1)*0.75 + m*l)/A

P(2,2,2) = 3/5 + (1.5+4)/30 = 3/5+5.5/30 = 23.5/30 ~ 0.7833
*/

import rosaIO.Task;

public class BS007_Iprb {
	final private static int AA = 0;
	final private static int Aa = 1;
	final private static int aa = 2;
	
	public static double probDominant(int k, int m, int l) {
		int n = m+l+k;
		return (k*(n+m+l-1) + m*(m-1)*0.75 + m*l)/(n*(n-1));
	}
	public static void main(String[] args) {
		Task io = new Task("iprb", args);
		int nums[] = io.scanner.readIntArray();
		if (nums.length>=3)
			io.printer.println(probDominant(nums[AA], nums[Aa], nums[aa]));
		io.close();
	}
}
