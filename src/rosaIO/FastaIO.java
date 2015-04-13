package rosaIO;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class FastaIO {
	public static Fasta[] listToFasta(List <String> ls){
		if (ls==null)
			return null;
		List <Fasta> lf = new ArrayList<Fasta>();
		Fasta fst=null;
		StringBuffer sb= new StringBuffer();
		for (int i=0; i<ls.size(); i++) {
			if (ls.get(i).charAt(0)=='>') {
				if (fst!=null)
					fst.dna = sb.toString();
					sb.setLength(0);
				fst = new Fasta();
				lf.add(fst);
				fst.label = ls.get(i).substring(1);
			}
			else sb.append(ls.get(i));//wasted if file doesn't start with '>'
		}
		if (lf.size()==0)
			return null;
		fst.dna = sb.toString();
		return (lf.toArray(new Fasta[0]));
	}
	public static Fasta[] fileToFastaArray(String path) {
		return listToFasta(RosaIO.readFileToList(path, Charset.defaultCharset()));
	}
	public static Fasta[] inputToFastaArray() {
		return listToFasta(RosaIO.readInputToList());
	}
//	-----------------------------------------------
	public static void printFastaArray (Fasta fsta[]) {
		for (int i = 0; i < fsta.length; i++) {
			System.out.println("label: "+fsta[i].label);
			System.out.println("data: "+fsta[i].dna);
		}
	}
}
