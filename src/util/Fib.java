package util;
import java.math.*;
public class Fib {
//Fibonacci with factor k: F(n) = F(n-1)+F(n-2)*k (k=1 - normal Fibonacci)
	public static BigInteger fib(int n, int k) {
		if (n<=0 || k<1)
			return BigInteger.ZERO;
		BigInteger cur=BigInteger.ONE, prev=cur, prevprev = BigInteger.ZERO;
		while (--n>0) {
			cur = prev.add(prevprev.multiply(BigInteger.valueOf(k)));
			prevprev=prev;
			prev = cur;
		}
		return cur;
	}
//mortal fib rabbits: Fm(n)=Fm(n-2)+...+Fm(n-m), or reformulating:
//Fm(n)=Fm(n-1)+Fm(n-2)-Fm(n-m-1)
	public static BigInteger fib_m(int n, int m){
//cache will store last m+1 results and put the current to m+2
		BigInteger cache[] = new BigInteger[m+2];
		int i;
		for (i=0; i<=n; i++) {
			if (i<3)
				cache[i] = BigInteger.ONE;
			else if (i<=m)
				cache[i] = cache[i-1].add(cache[i-2]);
			else {
				cache[m+1] = cache[m].add(cache[m-1]).subtract(cache[0]);
//shift cache 1 position left (I need memmove here :))
				System.arraycopy(cache, 1, cache, 0, cache.length-1);
			}
		}
		if (n>m)
			return cache[m+1];
		else
			return cache[i-1];					
	}
}
