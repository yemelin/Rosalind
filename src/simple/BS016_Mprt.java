package simple;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rosaIO.Fasta;
import rosaIO.RosaScanner;
import rosaIO.Streams;
import rosaIO.Task;

public class BS016_Mprt {
	public static final String uniprot_url = "http://www.uniprot.org/uniprot/";
	public static Pattern glyco_re = Pattern.compile("N[^P][ST][^P]");
	
	public static Fasta readProtFromUniprot (String label) {
		String url = "http://www.uniprot.org/uniprot/"+label+".fasta";
		InputStream is = Streams.obtainUrlStream(url);
		RosaScanner rs = new RosaScanner(is);
		Fasta[] fsta = rs.readFastaArray();
		return fsta[0];
	}
	
	public static int[] findAllOneBased(String s, Pattern p) { 
		Matcher m = p.matcher(s);
		int[] ret;
		int i;
		int[] tmp = new int[s.length()];
		for (i=0; m.find(); tmp[i++] = m.start()+1);//+1 - 1 based numbering
		ret = new int[i];
		System.arraycopy(tmp, 0, ret, 0, i);
		return ret;
	}
	
	public static void main(String[] args)  {
		Task io = new Task("mprt", args);
		List<String> uniprot_labels = io.scanner.readList();
		for (String label : uniprot_labels) {
			Fasta fst = readProtFromUniprot(label);
			int[] allPos =findAllOneBased(fst.dna, glyco_re); 
			if (allPos.length>0) {
				io.printer.println(label);
				io.printer.printArray(allPos);
			}
		}
	}
}
