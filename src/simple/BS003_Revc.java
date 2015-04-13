package simple;
import java.util.Scanner;
public class BS003_Revc {
	private static final String pattern = "ACGT";
	private static final char ptrn[] = {'A','C','G','T',};

//using Arrays
	public static int[] countCharsa(char s[]) {
		int i;
		int ret[] = new int[ptrn.length];
		for (char b : s) {
//just for fun. No profit from binary search, as ptrn too short
			if ((i=java.util.Arrays.binarySearch(ptrn, b))>=0)
				ret[i]++;
		}
		return ret;
	}
//using String	
	public static int[] countChars(String s){
		int ret[] = new int[pattern.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < pattern.length(); j++) {
				if (s.charAt(i)==pattern.charAt(j)) {
					ret[j]++;
					break;
				}
			}
		}
		return ret;
	}
	public static void printArray(int a[]){
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i<a.length-1)
				System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextLine()) {
			String st = sc.nextLine();
			printArray(countChars(st));
			printArray(countCharsa(st.toCharArray()));
		}
		sc.close();
	}
}
