package simple;

import java.math.BigInteger;

import util.InOut;
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
		int input[] = InOut.readInt(2);
		if ((input!=null) && (input[0]>=input[1])) {
			System.out.println
			(MathStats.partPerm(input[0], input[1]).mod(BigInteger.valueOf(1000000)));
			System.out.println("Recurrent alternative, for comparison: "+
					factFallMod(input[0]-input[1], input[0], 1000000));
		}
		else
			System.out.println("Input corrupted");
		
		
	}
}
