package bs059_Full;

// TODO: mark and unmark the suffixes as used when prefixes are marked
// TODO: cleanup and organize normally to functions and classes
import rosaIO.Rstring;
import rosaIO.Task;

public class BS059_Full {

	public static void main(String[] args) {
		Task io = new Task("full", args);
		double total = io.scanner.readDouble();
		Spectrum sp = new Spectrum();
		sp.weights = io.scanner.readDoubleArray();
		Rstring.loadMassTable();
		sp.fillParts();
		for (Part p : sp.parts) {
			System.out.println(p.id+": "+p.weight+", next:");
			for (int i=0; i<p.nextParts.size(); i++) {
				System.out.println(p.nextParts.get(i).id+" "+
						p.nextParts.get(i).weight+" "
						+p.nextProts.get(i));
			}
		}
		Part curPart = sp.parts[0], nextPart;
		int tsz = sp.parts.length/2-1;
		int m = 0;
		while ((curPart.hasNext() || curPart.prev!=null) && m!=tsz) {
			if (!curPart.hasNext() || 
					(nextPart = curPart.next()).id >sp.parts.length - tsz +m) {
				curPart._next = 0;
				curPart.used = false; //here suffix should also be marked!!!
				curPart = curPart.prev;
				m--;
			}
			else {
				if (!nextPart.used) {
					nextPart.prev = curPart;
					curPart = nextPart;
					curPart.used = true;//here suffix should also be marked!!!
					m++;
				}
			}
		}
		if (m==tsz) {
			System.out.println("Got it.");
			StringBuffer sb = new StringBuffer();
			sb.setLength(m);
			while (curPart.prev!=null) {
//				System.out.println("next prot");
				curPart = curPart.prev;
//				System.out.print(curPart.nextProts.get(curPart._next-1));
				sb.setCharAt(--m, curPart.nextProts.get(curPart._next-1));
			}
			System.out.println(sb.toString());
		}
	}

}
