package simple;

import rosaIO.Rstring;
import rosaIO.Task;

public class BS052_Spec {
	public static void main(String[] args) {
		Task io = new Task("seto", args);
		Rstring.loadMassTable();
		double t[] = io.scanner.readDoubleArray();
		StringBuffer sb = new StringBuffer();
		for (int i=1; i<t.length; i++) {
//			System.out.println("mass is:"+(t[i]-t[i-1]));
			sb.append(Rstring.getProtByMass(t[i]-t[i-1], 0.001));
		}
		io.printer.println(sb.toString());
	}
}
