package simple;

import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;
import rosaIO.Rstring;

//http://rosalind.info/problems/eval/
// TODO: organize normal input!!!
public class BS047_Eval {

	public static double[] strToDoubleArray(String s) {
		double[] d = new double[20];
		int i=0;
		for (String  st : s.split("\\s")) {
			try {
				d[i++] = Double.parseDouble(st);
			}
			catch (NumberFormatException nfe){}
		}
		double d2[] = new double[i];
		System.arraycopy(d, 0, d2, 0, i);
		return d2;
	}
	public static void main(String[] args) {
		InputStream is = RosaIO.obtainInputStream(args);
		Scanner sc = new Scanner(is);
		int n = sc.nextInt();
		sc.nextLine();
		String pattern = sc.nextLine();
		double gcs[];// = {0.25, 0.5, 0.75,};
		String inp = sc.nextLine();
//		System.out.println("pattern="+pattern+"\ninp="+inp);
		gcs = strToDoubleArray(inp);
//		System.out.println(n+" \n"+java.util.Arrays.toString(gcs)+"\n"+inp);
		double output[] = new double[gcs.length];
		for (double gc : gcs)
			System.out.printf("%.5f ", (n-pattern.length()+1)*Rstring.dnaProb(pattern, gc));
		System.out.println();
	}
}
