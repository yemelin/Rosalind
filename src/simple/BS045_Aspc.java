package simple;

import java.io.InputStream;
import java.math.BigInteger;

import rosaIO.RosaIO;
import util.InOut;
import util.MathStats;
import util.Modulos;

//http://rosalind.info/problems/aspc/
// [ C(n,n)+C(n,n-1)+...+C(n,m) ] modulo 1000000
// C(n,k) = n!/(k!(n-k)!), C(n,k-1) = C(n,k)*k/(n-k+1) 
public class BS045_Aspc {
	
//	straightforward and slow, uncomment only to check the result
	public static BigInteger sumCombsBrute (int n, int lo, int hi) {
		BigInteger ret = BigInteger.ZERO;
		for (int k=lo; k<=hi; k++)
			ret = ret.add(MathStats.comb(n, k));
		return ret;
	}

	public static BigInteger sumCombsMod (int n, int lo, int hi, int modulo) {
		BigInteger curComb = MathStats.comb(n, hi);
		BigInteger ret = curComb;
		for (int k=hi-1; k>=lo; k--) {
			curComb = curComb.multiply(BigInteger.valueOf(k+1))
							.divide(BigInteger.valueOf(n-k));
			ret = ret.add(curComb);
			if (modulo>0)
				ret = ret.mod(BigInteger.valueOf(modulo));
		}
		return ret;
	}
	
	public static void main(String[] args) {
		InputStream is = RosaIO.obtainInputStream(args);
		int[] input = InOut.readInt(is, 2);
		if (input!=null) {
			int n = input[0];
			int m = input[1]; 
//			System.out.println(sumCombsBrute(n, m, n).mod(BigInteger.valueOf(1000000)));
			System.out.println(sumCombsMod(n, m, n, Modulos.ROSAMODULO));
		}
	}
}
