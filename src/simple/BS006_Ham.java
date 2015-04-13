package simple;

import java.nio.charset.Charset;
import java.util.List;

import rosaIO.RosaIO;
public class BS006_Ham {
	public static int hamming (String s1, String s2) {
		int ret=0;
		if (s1.length()!=s2.length())
			ret = -1;
		else
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i)!=s2.charAt(i))
					ret++;
			}
		return ret;
	}
	public static void main(String[] args) {
		List <String> ls;
		if (args.length>0)
			ls = RosaIO.readFileToList(args[0], Charset.defaultCharset());
		else ls = RosaIO.readInputToList();
		if (ls!=null && ls.size()>1)
			System.out.println(hamming(ls.get(0), ls.get(1)));
	}
}
