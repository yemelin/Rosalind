//http://rosalind.info/problems/sset/
package simple;

// number of subsets  - just 2^N. Simplest explanation:
//{a1,a2,a3,...,an}   - elements
//  1, 0, 1, ... 1    - included to subset or not

import rosaIO.Task;
import util.Modulos;

public class BS044_Sset {

	public static void main(String[] args) {
		Task io = new Task("sset", args);
		io.printer.println(Modulos.powerOfTwoMod(io.scanner.readInt(), 1000000));
	}

}
