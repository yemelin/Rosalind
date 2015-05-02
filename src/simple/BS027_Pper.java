package simple;

import java.math.BigInteger;

import rosaIO.Task;
import util.MathStats;
public class BS027_Pper {
//	multiply and take modulo, avoid overflowing integers. Not using BigIntegers
//	for the beginning, assume modulo<=1000000
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
	
	public static void main(String[] args) {
		Task io = new Task("pper", args);		
		int n = io.scanner.readInt();
		int m = io.scanner.readInt();
		if (n>=m) {
			io.printer.println
			(MathStats.partPerm(n, m).mod(BigInteger.valueOf(1000000)));
			System.out.println("Recurrent alternative, for comparison: "+
					factFallMod(n-m, n, 1000000));
		}
		else
			System.out.println("Input corrupted");
		io.close();
	}
}
