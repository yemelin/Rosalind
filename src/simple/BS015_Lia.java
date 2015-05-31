package simple;

import rosaIO.Task;
import util.MathStats;

//Any organism mating with AaBb produces AaBb organism with P = 0.5*0.5
//Probability of N-th generation having >=m AaBb is probability of NOT
//having <m, which is in turn the sum of probabilities of having 1, 2... m-1,
//which is exactly the Binomial distribution function
public class BS015_Lia {
	private final static double prob_AaBb = 0.25; //0.5*0.5, baseP
	public static void main(String[] args) {
		Task io = new Task("lia", args);
		int gen = io.scanner.readInt(); //N = 2^gen
		int m = io.scanner.readInt();
//	for this task assume gen<=31
		io.printer.println(1f-MathStats.binomDistrib(prob_AaBb, m-1, 1<<gen));
		io.close();
	}
}
