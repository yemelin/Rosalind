package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Modulos {
//	multiply and take modulo, avoid overflowing integers. Not using BigIntegers
//	for the beginning, assume modulo<=1000000
	public static final long HALFLONGMAX = Long.MAX_VALUE/2;
	private static int multMod (int m, int n, int modulo) {
		int bigger = m>n? m:n;
		int smaller = m>n? n:m;
		if (smaller == 0)
			return 0;
		if (bigger>Integer.MAX_VALUE/smaller) 
			return multMod (bigger/2, smaller, modulo) +
					multMod (bigger-bigger/2, smaller, modulo);
		return (bigger * smaller) % modulo;
	}
	
	public static int factFallMod(int lo, int hi, int modulo) {
		int ret = 1;
		for (int i=lo+1; i<=hi; i++) {
			ret = multMod (ret, i, modulo);			
		}
		return ret;
	}
	
//power of 2 taken by modulo. 	
	public static long powerOfTwoMod(int n, int modulo) {
		long ret = 1;
//		int maxShift = 32;
		while (n-->0) {
			ret = ret<<1;
			if (ret>=HALFLONGMAX) 				
				ret = ret%modulo;
		}
		if (ret>modulo)
			ret = ret%modulo;
		return ret;
	}
//	simple BigInteger version. Beats long (or int) version of power of 2
//	by modulo on immensely huge numbers (starting from 2^60000 :))
	public static BigInteger powerOfTwoBigMod (int n, int modulo) {
		return BigInteger.valueOf(2).pow(n).mod(BigInteger.valueOf(modulo));
	}
	public static void main(String[] args) {
		int n=50000;
		int modulo = 1000000;
		long start;
		long stop;
		long total;
		long ret;
		BigInteger retBig;
		
		System.out.print("Int:   ");
		start = System.nanoTime();
		ret = powerOfTwoMod(n, modulo);
		stop = System.nanoTime();
		total = (stop - start);
		System.out.println(ret+" time:"+total);
		
		System.out.print("BigInt:");
		start = System.nanoTime();
		retBig = powerOfTwoBigMod(n, modulo);
		stop = System.nanoTime();
		total = (stop - start);
		System.out.println(retBig+" time:"+total);
	}
}
