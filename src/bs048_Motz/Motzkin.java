//http://rosalind.info/problems/sort/
package bs048_Motz;

import java.math.BigInteger;
// TODO: nucleoBonding should be moved to the library
// TODO: Motzkin and Catalan numbers should belong to some specific library
public class Motzkin {
	private static char nucleoBonding(char c) {
		char ret;
		switch (c) {
			case 'A': ret = 'U'; break;
			case 'C': ret = 'G'; break;
			case 'G': ret = 'C'; break;
			case 'U': ret = 'A'; break;
			default: ret = c;
		}
		return ret;
	}

	public static BigInteger motzkinDnaBig (String dna) {
		int len = dna.length();
		if (len==0 || len==1) 
			return BigInteger.ONE;
// motzByLength[i][j] stores Motzkin number for the string of length i
// starting at j
		BigInteger motzByLength[][] = new BigInteger[len+1][];
		motzByLength[0] = new BigInteger[dna.length()+1];
		for (int j=0; j<=dna.length(); j++) {
			motzByLength[0][j] = BigInteger.ONE;
		}
		for (int i=1; i<=len; i++) {
			motzByLength[i] = new BigInteger[dna.length()-i+1];
			for (int j=0; j<motzByLength[i].length; j++) {
				motzByLength[i][j] = motzByLength[i-1][j];
				for (int k=1; k<=i;k++) {					
//	possibly, the failure happends with k==i
					if (dna.charAt(j+i-1) == nucleoBonding(dna.charAt(j+k-1))) {
//						try {          //DEBUG
						motzByLength[i][j] = motzByLength[i][j]
								.add(motzByLength[i-k-1][j+k]
								.multiply(motzByLength[k-1][j]))
								.mod(BigInteger.valueOf(1000000));
//						}
//						catch (ArrayIndexOutOfBoundsException e){
//							System.out.println("i="+i+", k="+k+", j="+j
//									+", length="+ motzByLength[k].length);
//							System.out.println(motzByLength[i][j]);
//							System.exit(1);
//						}
					}
				}
			}
		}
		return motzByLength[len][0];
	}
}
