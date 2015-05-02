package simple;

import java.math.BigInteger;
import rosaIO.Task;
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
		Task io = new Task("aspc", args);
		int n = io.scanner.readInt();
		int m = io.scanner.readInt();
		if (n>=m) {
//			System.out.println(sumCombsBrute(n, m, n).mod(BigInteger.valueOf(1000000)));
			io.printer.println(sumCombsMod(n, m, n, Modulos.ROSAMODULO));
		}
		else
			System.err.println("Input corrupted");
		io.close();
	}
}
