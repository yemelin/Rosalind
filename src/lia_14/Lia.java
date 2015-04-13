package lia_14;
import java.util.Scanner;

import util.MathStats;

public class Lia {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gen = sc.nextInt();
		int m = sc.nextInt();
		System.out.println(1f-MathStats.distrib(0.25, m-1, 1<<gen));
		sc.close();
	}
}
