package rosaIO;

import java.util.LinkedList;
import java.util.List;

public class Fasta {
	public String label;
	public String desc;
	public String dna;
	public static LinkedList<Fasta> listToFastaList (List <String> ls){
			if (ls==null || ls.size()==0)
				return null;
			LinkedList <Fasta> lf = new LinkedList<Fasta>();
			Fasta fst=null;
			StringBuffer sb= new StringBuffer();
			for (int i=0; i<ls.size() && ls.get(i).length()!=0; i++) {
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
				return null; //maybe should return an empty array
			fst.dna = sb.toString();
			return lf;
		}
}
