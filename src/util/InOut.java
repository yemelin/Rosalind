package util;
import java.io.InputStream;
import java.util.Scanner;
public class InOut {
//	TODO check for corrupted input or insufficient input
//	TODO solve the scanner "resource leak"/"stdin closed" issue
	public static int[] readInt (int n){
		int a[] = new int[n];
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<n; i++) {
			if (sc.hasNextInt()) {
				a[i] = sc.nextInt();
				System.out.println("read "+ a[i]);
			}
			else
				return null;
		}
//		if we close scanner now, next Scanner will be unable to read from system.in!
//		sc.close();
		return a;
	}
	
	public static int[] readInt (InputStream is, int n){
		int a[] = new int[n];
		Scanner sc = new Scanner(is);
		for (int i=0; i<n; i++) {
			if (sc.hasNextInt()) {
				a[i] = sc.nextInt();
			}
			else
				return null;
		}
//		if we close scanner now, next Scanner will be unable to read from system.in!
//		sc.close();
		sc.close();
		return a;
	}
	
	public static double[] readDouble (int n) {
		double a[] = new double[n];
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<n; i++) {
			if (sc.hasNextDouble())
				a[i] = sc.nextDouble();
			else
				return null;
		}
		return a;
	}
}
