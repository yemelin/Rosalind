package simple;

import java.nio.charset.Charset;

import rosaIO.RosaIO;
import rosaIO.Rstring;

public class BS020_Ptrm {
	public static void main(String[] args) {
		String s;
		if (args.length>0) {
			s = RosaIO.readFile(RosaIO.DATAPATH+args[0], Charset.defaultCharset());
		}
		else
			s = RosaIO.readInput();
		Rstring.loadMassTable();
		System.out.println(Rstring.getMass(s));
	}
}
