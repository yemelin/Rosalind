package bs042_Rear;

public class BrokenReversal {
	public int[] breaks;
	public int[] perm;
	public BrokenReversal(int[] prevPerm, int i, int j) {
//		System.out.println("reversing "+i+" "+j);
		perm = Reversal.reverse(prevPerm, i, j-1);
//		System.out.println(Arrays.toString(perm));
		bpc = countBreakPoints();
//		System.out.println("break points:");
//		System.out.println(Arrays.toString(breaks));
	}
	int bpc;
	@Override
	public boolean equals (Object br) {
//		System.out.println("equals");
		int [] a = ((BrokenReversal)br).perm;
		for (int i = 0; i < a.length; i++) {
			if (a[i]!=perm[i])
				return false;
		}
		return true;
	}
//	count break points meanwhile creating the array of their positions
	public int countBreakPoints() {
		int bpc = 0;
		int[] tmpbreaks = new int[perm.length+1];
		if (perm[0]!=1) 
			bpc++;
		for (int i = 0; i < perm.length-1; i++) {
			if (perm[i]-perm[i+1]!=1 && perm[i]-perm[i+1]!=-1) {
				tmpbreaks[bpc++] = i+1;
			}
		}
//		System.out.println("bpc is "+bpc);
		if (perm[perm.length-1]!=perm.length) 
			tmpbreaks[bpc++]=perm.length;
		breaks = new int[bpc];
		System.arraycopy(tmpbreaks, 0, breaks, 0, bpc);
		return bpc;
	}
}
