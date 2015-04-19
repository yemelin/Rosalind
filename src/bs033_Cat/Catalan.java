package bs033_Cat;

import java.math.BigInteger;

//TODO: cleanup everything, add to libraries what's necessary
public class Catalan {
//	n-th catalan number
	public static int catalan(int n) {
		if (n==0)
			return 1;
		int ret = 0;
		for (int i=1; i<=n; i++) {
			ret+=catalan(i-1)*catalan(n-i);
		}
		return ret;
	}
	public static int catalanNonRec(int n) {
		int [] rets = new int[n+1];
		rets[0] = 1;
		for (int i=1; i<=n; i++) {
			for (int j=1;j<=i;j++) {
				rets[i]+=rets[j-1]*rets[i-j];
			}
		}
		return rets[n];
	}
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
	
	public static BigInteger catalanDnaRec (String dna) {
		if (dna.length()<=2)
			return BigInteger.ONE;
		depth++;
		int n=-1;
		BigInteger ret = BigInteger.ZERO;
		while ((n=nextValidBonding(dna,n+1))!=-1) {
//			System.out.println("Recursion depth: "+depth);
			if (depth==1) {
			System.out.print("dna: "+dna+", n="+n+", ");
			System.out.println ("split to: "+ dna.substring(1, n)+" and "
					+ dna.substring(n+1)); }
			ret=ret.add(catalanDnaRec(dna.substring(1, n)).multiply(
					catalanDnaRec(dna.substring(n+1)))).mod(BigInteger.valueOf(1000000));
//			System.out.println("ret="+ret);
		}
		depth--;
		return ret;
	}
//	Recursion fails. Stack overflow. Probably can be fixed by caching the sub-results
	public static BigInteger catalanDnaRec (String dna, int start, int end) {
		if (dna.length()<=2)
			return BigInteger.ONE;
		depth++;
		int n=-1;
		BigInteger ret = BigInteger.ZERO;
		while ((n=nextValidBonding(dna,n+1))!=-1) {
//			System.out.println("Recursion depth: "+depth);
			if (depth==1) {
			System.out.print("dna: "+dna+", n="+n+", ");
			System.out.println ("split to: "+ dna.substring(1, n)+" and "
					+ dna.substring(n+1)); }
//			try {
			ret=ret.add(catalanDnaRec(dna, start+1, start+n).multiply(
					catalanDnaRec(dna, start+n+1, end))).mod(BigInteger.valueOf(1000000));
//			}
//			catch (StackOverflowError e) {
//				System.out.println("yep, overflow");
//				return ret;
//			}
//			System.out.println("ret="+ret);
		}
		depth--;
		return ret;
	}

	public static final String RNA = "ACGU";
	private static void updateCounters (int[] counters, String pattern, char c) {
		int n = pattern.indexOf(c);
		if (n!=-1)
			counters[n]++;
	}
	
	private static int nextValidBonding(String dna, int start) {
		if (dna.length()<2)
			return -1;
		
		int [] counters=new int[RNA.length()];
		for (int i=start; i<dna.length(); i++) {
			updateCounters(counters, RNA, dna.charAt(i));
			if (dna.charAt(i)==nucleoBonding(dna.charAt(0)) &&
					counters[0]==counters[3] &&
					counters[1]==counters[2]) {
//				System.out.println("next bonding for "+dna+"from "+start+": "+i);
				return i;
			}
		}
		return -1;
	}
	static int depth=0;
	
//	basic, fails on big strings due to multiplication (1000000^2 > MAXINT)
	public static int catalanDna (String dna) {
		int nbp = dna.length()/2;//number of base pairs
		if (nbp==0) return 1;
//		System.out.print("nbp "+nbp+": ");
		int catByLength[][] = new int[nbp+1][];
		catByLength[0] = new int[dna.length()+1];
		for (int j=0; j<=dna.length(); j++) {
			catByLength[0][j] = 1;
		}
		for (int i=1; i<=nbp; i++) {
			catByLength[i] = new int[dna.length()-i*2+1];
			for (int j=0; j<catByLength[i].length; j++) {
				for (int k=1; k<=i;k++) {
					if (dna.charAt(j) == nucleoBonding(dna.charAt(j+k*2-1))) {
//						System.out.println("i="+i+", j="+j+", "
//								+ "char="+dna.charAt(j)+
//								" char2="+dna.charAt(j+k*2-1));
						catByLength[i][j]=(catByLength[i][j]+ 
								catByLength[k-1][j+1]*catByLength[i-k][j+k*2])%1000000;
					}
				}
			}
		}
		return catByLength[nbp][0];
	}

	public static BigInteger catalanDnaBig (String dna) {
		int nbp = dna.length()/2;//number of base pairs
		if (nbp==0) return BigInteger.ONE;
//		System.out.print("nbp "+nbp+": ");
		BigInteger catByLength[][] = new BigInteger[nbp+1][];
		catByLength[0] = new BigInteger[dna.length()+1];
		for (int j=0; j<=dna.length(); j++) {
			catByLength[0][j] = BigInteger.ONE;
		}
		for (int i=1; i<=nbp; i++) {
			catByLength[i] = new BigInteger[dna.length()-i*2+1];
			for (int j=0; j<catByLength[i].length; j++) {
				catByLength[i][j]=BigInteger.ZERO;
				for (int k=1; k<=i;k++) {
					if (dna.charAt(j) == nucleoBonding(dna.charAt(j+k*2-1))) {
//						System.out.println("i="+i+", j="+j+", "
//								+ "char="+dna.charAt(j)+
//								" char2="+dna.charAt(j+k*2-1));
						try {
						catByLength[i][j] = catByLength[i][j]
								.add(catByLength[k-1][j+1]
								.multiply(catByLength[i-k][j+k*2]))
								.mod(BigInteger.valueOf(1000000));
						}
						catch (NullPointerException e){
							System.out.println("i="+i+", k="+k+", j="+j
									+", length="+ catByLength[k-1].length);
							System.out.println(catByLength[i][j]);
							System.out.println(catByLength[k-1][j+1]);
							System.out.println(catByLength[i-k][j+k*2]);
							System.exit(1);
						}
					}
				}
			}
		}
		return catByLength[nbp][0];
	}


}
