package simple;
//http://rosalind.info/problems/rstr/

import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;
// TODO: fix input. Finally, write a function for obtaining a stream.
import rosaIO.Rstring;

public class BS043_Rstr {

	public static void main(String[] args) {
		InputStream is = RosaIO.obtainInputStream(args);
		if (is==null)
			System.exit(1);
		Scanner sc = new Scanner(is);
		String dna="";
		int n=0;
		double gc=0;

		if (sc.hasNextLine())
			dna = sc.nextLine();
		if (sc.hasNextInt())
			n = sc.nextInt();
		if (sc.hasNextDouble())
			gc = 0.584477;
		if (dna.equals("") || n<=0 || gc<=0.0)
			System.out.println("Input corrupted.");
		else {
//	probability that a random string is equal to dna
			double dnaProb = Math.pow(10, Rstring.calculateLogProb(dna, gc));
			System.out.println(1 - Math.pow(1-dnaProb, n));
		}
	}
}
