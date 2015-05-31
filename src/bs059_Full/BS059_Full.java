package bs059_Full;

// TODO: cleanup and organize normally to functions and classes
import rosaIO.Task;
import util.MassTable;
// Algorithm used: for each Spectrum weight corresponding to a part of the peptide
// we form a list of potential next parts (whose weight is bigger than the current
// by one protein's weight). Then we try to build a sequence from each of them,
// reassigning curPart to one of the next, meanwhile each part keeps "iterator"
// position. If at some part a sequence fails, it's iterator state is reset, and
// we move one step back. So, recursive approach without direct recursion.
public class BS059_Full {

	public static void main(String[] args) {
		Task io = new Task("full", args);
		/*double total =*/ io.scanner.readDouble();//not needed
		Spectrum sp = new Spectrum();
		sp.weights = io.scanner.readDoubleArray();
		MassTable.loadMassTable();
		sp.fillParts();
////		DEBUG
//		for (Part p : sp.parts) {
//			System.out.println(p.id+": "+p.weight+", next:");
//			for (int i=0; i<p.nextParts.size(); i++) {
//				System.out.println(p.nextParts.get(i).id+" "+
//						p.nextParts.get(i).weight+" "
//						+p.nextProts.get(i));
//			}
//		}
		Part curPart = sp.parts[0], nextPart;
		int tsz = sp.parts.length/2-1;
		int m = 0;
		while ((curPart.hasNext() || curPart.prev!=null) && m!=tsz) {
			if (!curPart.hasNext() || 
					(nextPart = curPart.next()).id >sp.parts.length - tsz +m) {
				curPart._next = 0;
//	mark the part and it's suffix pair (they form the whole peptide together)
				curPart.used = false;
				sp.parts[ sp.parts.length-1-curPart.id].used = false;
				curPart = curPart.prev;
				m--;
			}
			else {
				if (!nextPart.used) {
					nextPart.prev = curPart;
					curPart = nextPart;
//	mark the part and it's suffix pair (they form the whole peptide together)
					curPart.used = true;
					sp.parts[ sp.parts.length-1-curPart.id].used = true;
					m++;
				}
			}
		}
		if (m==tsz) {
//			System.out.println("Got it.");
			StringBuffer sb = new StringBuffer();
			sb.setLength(m);
			while (curPart.prev!=null) {
				curPart = curPart.prev;
				sb.setCharAt(--m, curPart.nextProts.get(curPart._next-1));
			}
			io.printer.println(sb.toString());
		}
	}
}
