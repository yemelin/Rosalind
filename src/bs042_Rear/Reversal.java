package bs042_Rear;

import java.util.ArrayList;
import util.RosaArrays;

public class Reversal {
//	static int [] from = {3,5,2,1,4,};
//	static int [] to = {2,5,3,4,1};
	public static int[] getWorkingReversal (int[] from, int[]to) {
		int[] tmp = new int[to.length];
		int[] ret = new int[to.length];
		for (int i = 0; i < to.length; i++)
			tmp[ to[i]-1 ] = i;
		for (int i = 0; i < tmp.length; i++)
			ret[i] = tmp[ from[i]-1 ]+1;
		return ret;
	}

//	TODO: do not copy elements that will be swapped
	public static int[] reverse (int[] a, int i, int j) {
		int[] tmp = new int[a.length];
		System.arraycopy(a, 0, tmp, 0, a.length);
		for (; i<j ; i++, j--) 
			RosaArrays.swap(tmp,i,j);
		return tmp;
	}
	
	
	
	
	public static int countReversals (int[] from, int[] to) {
//		Scanner sc = new Scanner(System.in);
		ArrayList<BrokenReversal> brevs = new ArrayList<>();
		ArrayList<BrokenReversal> next = null;
		BrokenReversal tmpbr=new BrokenReversal(getWorkingReversal(from, to), 0, 0);
//		System.out.println(Arrays.toString(tmpbr.perm));
		int depth = 0;
		int minBreaks = from.length+2;//more than theoretical maximum of breaks
		brevs.add(tmpbr);
		OUTER:
		while (tmpbr.breaks.length!=0 && depth<9) {
//			System.out.println(depth);
			depth++;
//			sc.nextLine();
			next = new ArrayList<>();
			for (BrokenReversal br: brevs) {
//				System.out.println(Arrays.toString(br.perm));
//				System.out.println(Arrays.toString(br.breaks));
				for (int i=0; i<br.breaks.length-1; i++)
					for (int j=i+1; j<br.breaks.length; j++) {
						if (br.breaks[j]-br.breaks[i]>1) {
							tmpbr = new BrokenReversal(br.perm, br.breaks[i], br.breaks[j]);
//							if (!next.contains(tmpbr = new BrokenReversal(br.perm, br.breaks[i], br.breaks[j])))
							if (tmpbr.breaks.length<minBreaks) {
								minBreaks = tmpbr.breaks.length;
								next = new ArrayList<>();
//								System.out.println("Next reloaded for new minBreaks");
							}
							if (next.size()<20000)
								next.add(tmpbr);
//							System.out.println(tmpbr.breaks.length);
							if (tmpbr.breaks.length == 0)
								break OUTER;
						}
					}
			}
//			System.out.println("Got perms:"+next.size()+", breaks:"+minBreaks);
			brevs = next;
		}
//		System.out.println(depth);
		return depth;
	}
	static class PermPair {
		int[] from;
		int[] to;
		PermPair(int[] from, int[] to) {
			this.from = from;
			this.to = to;
		}
//	actually, it should also check the contents of arrays
		boolean inputOK() {
			return from.length==to.length;
		}
	}
}
