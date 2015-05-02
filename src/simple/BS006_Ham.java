package simple;

import java.util.List;

import rosaIO.Rstring;
import rosaIO.Task;

public class BS006_Ham {
	public static void main(String[] args) {
		Task io = new Task("ham", args);
		List <String> ls = io.scanner.readList();
		System.out.println(Rstring.hamming(ls.get(0), ls.get(1)));
		io.close();
	}
}
