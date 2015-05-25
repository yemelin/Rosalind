package simple;

import java.math.BigDecimal;
import java.math.BigInteger;

import rosaIO.Task;
import util.MathStats;

public class BS060_Indc {
//// P(X>=k)
//	public static double independent_segregation (int k, int n) {
//		double ret = 0.0;
//		BigDecimal p = BigDecimal.valueOf(2);
//		BigInteger combs = BigInteger.ZERO;
//		for (int i=n; i>=k; i--) {
//			combs = combs.add(MathStats.comb(n, i));
//		}
////		System.out.println("Combs = "+combs);
////		ret = Math.log10((new BigDecimal(combs).divide(p.pow(n))).doubleValue());
//		ret = Math.log10(combs.doubleValue()) - n*Math.log10(p.doubleValue());
//		return ret;
//	}

	public static double[] countAllSegregations (int n) {
		double ret[] = new double[n+1];
		BigInteger[] combs = new BigInteger[n+1];
		BigDecimal p = BigDecimal.valueOf(2);
		combs[n] = BigInteger.ZERO;
		for (int i = n; i > 0; i--) {
			combs[i] = combs[i].add(MathStats.comb(n, i));
//			System.out.println(combs[i]);
			ret[i] = Math.log10(combs[i].doubleValue()) - n*Math.log10(2);
//			ret[i] = Math.log10((new BigDecimal(combs[i]).divide(p.pow(n))).doubleValue());
			combs[i-1] = combs[i];
		}
		return ret;
	}
	public static void main(String[] args) {
		Task io = new Task("indc", args);
		int n = 2*io.scanner.readInt();
		double[] output = countAllSegregations(n);
		io.printer.printArray(output,1);
	}
}
