package util;

import java.util.Arrays;

public class MultiSets {
//	private double[] realloc(double [] x, int newCapacity) {
//		double[] tmp = new double[newCapacity];
//		System.arraycopy(x, 0, tmp, 0, Math.min(x.length, newCapacity));
//		return tmp;
//	}
	public static class Multiplicity {		//POJO
		Multiplicity (int c, double v) {
			count = c;
			value = v;
		}
		public final int getCount() {
			return count;
		}
		public final double getValue() {
			return value;
		}
		private final int count;
		private double value;
	}
	public static double[] minkSum (double[] mset1, double[] mset2) {
		double [] tmp = new double[mset1.length*mset2.length];
		int k=0;
		for (int i = 0; i < mset1.length; i++)
			for (int j = 0; j < mset2.length; j++)
				tmp[k++] = mset1[i]+mset2[j];
		return tmp;
	}
	public static double[] minkDiff (double[] mset1, double[] mset2) {
		double [] tmp = new double[mset1.length*mset2.length];
		int k=0;
		for (int i = 0; i < mset1.length; i++)
			for (int j = 0; j < mset2.length; j++)
				tmp[k++] = mset1[i]-mset2[j];
		return tmp;
	}
	public static Multiplicity countMultiplicity (double[] x, double precision) {
		double[] tmp = new double[x.length];
		System.arraycopy(x, 0, tmp, 0, x.length);
		Arrays.sort(tmp);
		System.out.println(Arrays.toString(tmp));
		int maxcount = 0, curcount=1;
		double maxval = 0.0;
		for (int i=0; i<tmp.length-1; i++) {
			if (Math.abs(tmp[i]-tmp[i+1])<precision)
				curcount++;
			else {
				if (curcount>maxcount) {
					maxcount=curcount;
					maxval=tmp[i];
				}
				curcount = 1;
			}
		}
		return new Multiplicity(maxcount, maxval);
	}
}
