package simple;

import java.nio.charset.Charset;

import rosaIO.RosaIO;
import rosaIO.Rstring;

public class BS008_Prot {
	public static void main(String[] args) {
		String s;
		if (args.length>0)
			s = RosaIO.readFile(args[0], Charset.defaultCharset());
		else
			s = RosaIO.readInput();
		if (s!=null)
			System.out.println(Rstring.rnaToProtein(s));
	}

}
