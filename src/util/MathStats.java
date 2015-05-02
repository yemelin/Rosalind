package util;
import java.math.*;

public class MathStats {

//(n!)/(m!) = n*...*(n-m+1)
	public static BigInteger factFall (int lo, int hi) {
		BigInteger ret;
		if (lo>hi)
			ret = BigInteger.valueOf(0);//error, factorial of negative integer
		else if (hi>lo) {
			ret = BigInteger.valueOf(hi);
			while (--hi>lo)
				ret = ret.multiply(BigInteger.valueOf(hi));
		}
		else
			ret = BigInteger.valueOf(1);	//0!=1, definition
		return ret;
	}
// C(n,m) = n!/ (m!(n-m)!)
	public static BigInteger comb(int n, int m){
		if (m<n-m)
			m = n-m;	//now n-m = "old m"		
		return factFall(m,n).divide(factFall(0,n-m));
	}
// A(n,m) = n!/(n-m)!	
	public static BigInteger partPerm(int n, int m) {
		return factFall(n-m, n);
	}
	
//	probability function of binomial distribution P(X=m)
	public static double binProb(double baseP, int m, int n) {
		BigDecimal p = BigDecimal.valueOf(baseP);
		BigDecimal no_p = BigDecimal.valueOf(1-baseP);
		return (new BigDecimal(comb(n,m))).multiply(p.pow(m)).
				multiply(no_p.pow(n-m)).doubleValue() ;
	}
//	binomial distribution function P(X<=m)
	public static double binomDistrib (double baseP, int m, int n) {
		double rt = 0.0;
		for (int i=0; i<=m; i++) {
			rt+=binProb(baseP, i, n);
		}
		return rt;
	}
}
