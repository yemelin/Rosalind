//http://rosalind.info/problems/iev/
package simple;
import rosaIO.Task;

public class BS013_Iev {

	public static void main(String[] args) {
		Task io = new Task("iev", args);
//	6 possible couples
		int AA_AA = io.scanner.readInt();
		int AA_Aa = io.scanner.readInt();
		int AA_aa = io.scanner.readInt();
		int Aa_Aa = io.scanner.readInt();
		int Aa_aa = io.scanner.readInt();
/*		int aa_aa = */io.scanner.readInt(); //not needed
		double exp_dom = 2*(AA_AA+AA_Aa+AA_aa) + //P=1 to have dominant phenotype
				1.5*(Aa_Aa+Aa_aa); //P=0.75 to have dominant phenotype
		io.printer.println(exp_dom);
	}

}
