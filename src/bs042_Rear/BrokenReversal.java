package bs042_Rear;

// it's "broken", because it is all about "breakpoints" (e.g 5,6,7 !!! 3,2,1...)
public class BrokenReversal {
	public int[] breaks;//permutations breakpoints
	public int[] perm;	//the new permutation itself
	public int bpc;		//breakpoints count
	public BrokenReversal prevBr; //previous reversal, which transforms to the current
//	by reversal on positions i+1, j, that are saved in revPoints
	public int[] revPoints; //reversal points from previous to the current
	
	public BrokenReversal(int[] prevPerm, int i, int j) {
		perm = Reversal.reverse(prevPerm, i, j-1);
		bpc = countBreakPoints();
	}
	
	public BrokenReversal(BrokenReversal br, int i, int j) {
		this(br.perm, i, j);
		prevBr = br;
		revPoints = new int[2];
		revPoints[0] = i+1;//1-based numbering
		revPoints[1] = j;  //j is a breakPoint position which is equal to
			//	the last strand's element's position in 1-based numbering
	}
	
	@Override
	public boolean equals (Object br) {
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
