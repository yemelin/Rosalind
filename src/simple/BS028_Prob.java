package simple;

import java.util.Scanner;

import rosaIO.Rstring;
import util.RosaArrays;

public class BS028_Prob {
	private static final int INITIAL_CAPACITY = 10;
	private static double[] readDouble() {
		Scanner sc = new Scanner(System.in);
		double[] a = new double[INITIAL_CAPACITY];
		int i;
		for (i=0; sc.hasNextDouble(); i++) {
			if (i>a.length-1)
				a = realloc(a, a.length*2);
			a[i] = sc.nextDouble();
		}
		return realloc(a, i);
	}
	private static double[] realloc(double[] a, int i) {
		double []b = new double[i];
		System.arraycopy(a, 0, b, 0, (a.length>i)? i:a.length);
		return b;
	}
//	private static void printDoubleArray(double[] logProbs) {
//		System.out.printf("%.5f ", logProbs[0]);
//		for (int i = 1; i < logProbs.length; i++) {
//			System.out.printf(" %.5f", logProbs[i]);
//		}
//		System.out.println();
//	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String dna;
		
		if (sc.hasNextLine()) {
			dna = sc.nextLine();
			double [] gcs = readDouble();
			System.out.println("got dna: "+ dna);
			System.out.println("Got gcs: "+ java.util.Arrays.toString(gcs));
			if (dna.length()==0 || gcs.length==0) {
				System.out.println("input corrupted");
				return;
			}
			double [] logProbs = new double [gcs.length];
			for (int i = 0; i < gcs.length; i++) {
				logProbs[i] = Rstring.calculateLogProb(dna, gcs[i]);
			}
			RosaArrays.printArray(logProbs);
		}
	}
}
