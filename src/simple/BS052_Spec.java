package simple;

import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;
import rosaIO.Rstring;

public class BS052_Spec {
	public static final int INITIAL_CAPACITY = 101;
	public static double[] readDouble(InputStream is) {
		double tmp[] = new double[INITIAL_CAPACITY];//fix the capacity!!!
		Scanner sc = new Scanner(is);
		int i=0;
		while (sc.hasNextDouble())
			tmp[i++]=sc.nextDouble();
		double ret[] = new double[i];
		System.arraycopy(tmp, 0, ret, 0, i);
		sc.close();
		return ret;
	}
	public static void main(String[] args) {
		Rstring.loadMassTable();
		InputStream is = RosaIO.obtainInputStream(args);
		double t[] = readDouble(is);
		StringBuffer sb = new StringBuffer();
		for (int i=1; i<t.length; i++) {
			System.out.println("mass is:"+(t[i]-t[i-1]));
			sb.append(Rstring.getProtByMass(t[i]-t[i-1], 0.001));
		}
		System.out.println(sb.toString());

	}

}
