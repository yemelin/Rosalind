package simple;
//import java.util.Iterator;
//import java.nio.charset.Charset;
import java.util.TreeSet;

import rosaIO.Fasta;
import rosaIO.FastaIO;
import rosaIO.Rstring;;
public class BS018_Orf {
	public static String[] findAllProts (String dna, TreeSet<String> set) {
		int start = 0;
		String nextprot;
		StringBuffer sb = new StringBuffer (dna.replace('T', 'U'));

		while ((start = sb.indexOf(Rstring.STARTCODON, start))!=-1) {			
			nextprot = Rstring.rnaToProtein(sb.substring(start));
//check if there is STOP in the end or just the end of the string			
			if (nextprot.length()==(sb.length()-start)/3)
				break;
//			System.out.println(nextprot);
			set.add(nextprot);
			start+=3;
		}
		return null;
	}
//TreeSet is used to add only distinct proteins
	public static String[] findAllProteins(String dna) {
		TreeSet<String> set = new TreeSet<String>();
		findAllProts (dna, set);
		findAllProts(Rstring.dnaReverseComplement(dna), set);
		return (String[]) set.toArray(new String[set.size()]);
	}

	public static void main(String[] args) {
//	TODO: check input for errors
		Fasta fst[];
		if (args.length>0)
			fst = FastaIO.fileToFastaArray(args[0]);
		else
			fst= FastaIO.inputToFastaArray();
		
		for (String st : findAllProteins(fst[0].dna))
			System.out.println(st);
	}
}
