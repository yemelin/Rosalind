package bs042_Rear;

import java.util.ArrayList;

import util.RosaArrays;

public class Reversal {
//	get a reversal, which reversal sequences to the identity permutation
//	(1,2,3...10) are equivalent to the sequences between the original two
//	permutations (like encoding from 1,2.. alphabet to "to" alphabet)
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
	
	
	public static BrokenReversal lastBr; //last one, saving it allows to
//	trace back the whole sequence. It's not good that it's global and it's not good that
//	it is a side effect of countReversals()

/*****************************************/	
// Algorithm is as follows: starting from "from" reversal, build the "next" list
//	of reversals by adding all possible reversals at the breakpoints, which minimize
//	the new number of breakpoints. It turned out I can't add all, so I add an arbitrary 
//	number 20000 (or less). Check whether the new reversal is already used should
//	be implemented (as in Breadth-first). Checking all the reversals is silly
//	and grows the complexity exponentially, but the check only on the reversals
//	with the same nyumber of breakpoints seems real
//	TODO: try set rather than list for "next"
//	TODO: implement a check on the reversals only with the same breakpoints count
	public static int countReversals (int[] from, int[] to) {
		ArrayList<BrokenReversal> brevs = new ArrayList<>();
		ArrayList<BrokenReversal> next = null;
		BrokenReversal tmpbr=new BrokenReversal(getWorkingReversal(from, to), 0, 0);
		int depth = 0;
		int minBreaks = from.length+2;//more than theoretical maximum of breaks
		brevs.add(tmpbr);
		OUTER:
		while (tmpbr.breaks.length!=0 && depth<9) {
			depth++;
			next = new ArrayList<>();
			for (BrokenReversal br: brevs) {
				for (int i=0; i<br.breaks.length-1; i++) {
					for (int j=i+1; j<br.breaks.length; j++) {
						if (br.breaks[j]-br.breaks[i]>1) {
							tmpbr = new BrokenReversal(br, br.breaks[i], br.breaks[j]);
							if (tmpbr.breaks.length<minBreaks) {
								minBreaks = tmpbr.breaks.length;
								next = new ArrayList<>();
							}
							if (next.size()<20000)
								next.add(tmpbr);
							if (tmpbr.breaks.length == 0)
								break OUTER;
						}
					}
				}
			}
			brevs = next;
		}
		lastBr = tmpbr;
		return depth;
	}
	public static ArrayList<BrokenReversal> sortingReversals() {
		ArrayList<BrokenReversal> brl = new ArrayList<>();
		BrokenReversal tmpBr = lastBr;
		brl.add(lastBr);
		while (tmpBr.prevBr!=null) {
			brl.add(tmpBr = tmpBr.prevBr);
		}
		return brl;
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
