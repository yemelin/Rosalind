package simple;

import java.util.Scanner;

import util.InOut;

public class IOtest {

	public static String readInput() {
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine())
			sb.append(sc.nextLine());
//		sc.close();
		return sb.toString();
	}
	public static void main(String[] args) {
//		String s = readInput();
//		System.out.println(s);
		Scanner sc = new Scanner (System.in);
		if (sc.hasNextLine())
			System.out.println(sc.nextLine());
		int [] a = InOut.readInt(2);
		System.out.println(a[0]+a[1]);
	}
}
