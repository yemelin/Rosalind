package simple;
import java.nio.charset.Charset;
import java.util.List;

import rosaIO.FastaIO;
import rosaIO.Fasta;
import rosaIO.RosaIO;
import rosaIO.Rstring;

public class BS005_Gc {
	public static void main(String[] args) {
		List <String> ls;
		Fasta [] fsta;
		double gc, maxgc = 0.0f;
		int maxfst=0;

		if (args.length>0)
			ls = RosaIO.readFileToList(args[0], Charset.defaultCharset());
		else
			ls = RosaIO.readInputToList();
		
		if ((fsta = FastaIO.listToFasta(ls))!=null) {
			for (int i = 0; i < fsta.length; i++) {
//				System.out.println(countGC(fsta[i].dna)+" "+fsta[i].label);
				if ((gc = Rstring.countGC(fsta[i].dna))>maxgc) {
					maxfst = i;
					maxgc = gc;
				}
			}
			System.out.println(maxgc);
			System.out.println(fsta[maxfst].label);
		}
	}
}
