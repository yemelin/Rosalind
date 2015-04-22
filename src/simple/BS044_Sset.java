package simple;

//http://rosalind.info/problems/sset/
// number of subsets  - just 2^N. Simplest explanation:
//{a1,a2,a3,...,an}   - elements
//  1, 0, 1, ... 1    - included to subset or not
import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;
import util.Modulos;

public class BS044_Sset {

	public static void main(String[] args) {
		InputStream is = RosaIO.obtainInputStream(args);
		Scanner sc = new Scanner(is);
		if (sc.hasNextInt())
			System.out.println(Modulos.powerOfTwoMod(sc.nextInt(), 1000000));
	}

}
