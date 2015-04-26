package simple;

import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;
import util.Sets;
// TODO: it's high time to organize normal file output!!! 
public class BS051_Seto {

	public static int[] toArray(String s) {
		int[] tmp = new int[20001];//fix the capacity!
		Scanner sc = new Scanner(s);
		sc.useDelimiter("[\\s,{} ]+");
		int i=0;
		while (sc.hasNextInt()) {
			tmp[i++] = sc.nextInt();
		}
		int ret[] = new int[i];
		System.arraycopy(tmp, 0, ret, 0, i);
		sc.close();
		return ret;
	}
	public static void main(String[] args) {
		InputStream is= RosaIO.obtainInputStream(args);
		Scanner lsc = new Scanner(is);
		int n = lsc.nextInt(); lsc.nextLine();
		int[] set1 = toArray(lsc.nextLine());
		int[] set2 = toArray(lsc.nextLine());
//		System.out.println(java.util.Arrays.toString(set1));
//		System.out.println(java.util.Arrays.toString(set2));
		Sets.printSet((Sets.union(set1, set2)));
		Sets.printSet(Sets.intersect(set1, set2));
		Sets.printSet(Sets.diff(set1, set2));
		Sets.printSet(Sets.diff(set2, set1));
		Sets.printSet(Sets.not(set1, n));
		Sets.printSet(Sets.not(set2, n));
		lsc.close();
	}
}
