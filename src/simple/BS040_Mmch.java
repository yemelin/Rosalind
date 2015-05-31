package simple;

import java.math.BigInteger;

import rosaIO.Fasta;
import rosaIO.Task;
import util.MathStats;
import util.Rstring;


public class BS040_Mmch {
//	to count the number of possible maximum matchings we just count the number
//	of ways to delete extra nucleotides without pairs (complements), i.e:
//	CUUU - delete two 'U' and multiply it by the number of perfect matchings
//	in the resulted graph: C(n,m)*P(m) = A(n,m) = n!/(n-m)!
	public static BigInteger countMatchings (String dna) {
		int counts[] = Rstring.countChars(dna, "AGCU");
		int lo1, hi1, lo2, hi2;
		if (counts[0]<counts[3]) {
			lo1=counts[0]; 
			hi1=counts[3];
		}
		else {
			lo1=counts[3]; 
			hi1=counts[0];
		}
		if (counts[1]<counts[2]) {
			lo2=counts[1]; 
			hi2=counts[2];
		}
		else {
			lo2=counts[1]; 
			hi2=counts[2];
		}
		BigInteger ret = MathStats.partPerm(hi1, lo1)
				.multiply(MathStats.partPerm(hi2, lo2));
		return ret;
	}
	
	public static void main(String[] args) {
		Task io = new Task("mmch", args);
		Fasta[] fsta = io.scanner.readFastaArray();
		io.printer.println(countMatchings(fsta[0].dna));
		io.close();
	}

}
