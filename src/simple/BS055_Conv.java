package simple;

import rosaIO.RosaScanner;
import rosaIO.Task;
import static util.MultiSets.*;

public class BS055_Conv {

	public static void main(String[] args) {
		Task io = new Task("conv", args);
		double[] mset1 = new RosaScanner(io.scanner.readLine()).readDoubleArray();
		double[] mset2 = new RosaScanner(io.scanner.readLine()).readDoubleArray();
		Multiplicity m = countMultiplicity(minkDiff(mset1, mset2), 0.000005);
		io.printer.println(m.getCount());
		io.printer.println(m.getValue());
		io.close();
	}

}
