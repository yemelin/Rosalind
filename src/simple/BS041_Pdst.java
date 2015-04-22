package simple;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import util.RosaArrays;

public class BS041_Pdst {
	public static double distance(String s1, String s2) {
		double ret;
		int n=0;
		if (s1.length()!=s2.length())
			ret = -1.0;
		else {
			for (int i=0; i<s1.length(); i++)
				if (s1.charAt(i)!=s2.charAt(i))
					n++;
			ret = (double)n/s1.length();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Fasta fsta[] = FastaIO.obtainFastaArray(args, 0);
		if (fsta!=null && fsta.length>0) {
			double distances [][] = new double[fsta.length][fsta.length];
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < distances.length; j++) {
					distances[i][j] = distance(fsta[i].dna, fsta[j].dna);
				}
				RosaArrays.printArray(distances[i]);
			}
			
		}
	}
}
