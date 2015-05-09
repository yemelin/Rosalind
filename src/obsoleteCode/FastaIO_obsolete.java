package obsoleteCode;


import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import rosaIO.Fasta;
import rosaIO.Streams;


public class FastaIO_obsolete {
	public static LinkedList<Fasta> fileToFastaList(String path) {
		return Fasta.listToFastaList(RosaIO_obsolete.readFileToList(path, Charset.defaultCharset()));
	}
	public static LinkedList<Fasta> inputToFastaList() {
		return Fasta.listToFastaList(RosaIO_obsolete.readInputToList());
	}
	public static Fasta[] fileToFastaArray(String path) {
		return listToFasta(RosaIO_obsolete.readFileToList(path, Charset.defaultCharset()));
	}
	public static Fasta[] inputToFastaArray() {
		return listToFasta(RosaIO_obsolete.readInputToList());
	}
// automatic choice between file (from args[0]) and terminal	
	public static Fasta[] obtainFastaArray(String[]args, int requiredLength) {
		Fasta[] fsta;
		if (args.length>0)
			fsta = fileToFastaArray(Streams.DATAPATH+args[0]);
		else
			fsta = inputToFastaArray();
		if (fsta==null || fsta.length<requiredLength) {
			prepareToExit();
			System.exit(1);
		}
		return fsta;
	}

private static void prepareToExit() {
	System.out.println("Input corrupted, program terminated");
}
//	-----------------------------------------------
	public static void printFastaArray (Fasta fsta[]) {
		for (int i = 0; i < fsta.length; i++) {
			System.out.println("label: "+fsta[i].label);
			System.out.println("data: "+fsta[i].dna);
		}
	}
	//	public static Fasta[] listToFasta(List <String> ls){
	//		if (ls==null)
	//			return null;
	//		List <Fasta> lf = new ArrayList<Fasta>();
	//		Fasta fst=null;
	//		StringBuffer sb= new StringBuffer();
	//		for (int i=0; i<ls.size(); i++) {
	//			if (ls.get(i).charAt(0)=='>') {
	//				if (fst!=null)
	//					fst.dna = sb.toString();
	//					sb.setLength(0);
	//				fst = new Fasta();
	//				lf.add(fst);
	//				fst.label = ls.get(i).substring(1);
	//			}
	//			else sb.append(ls.get(i));//wasted if file doesn't start with '>'
	//		}
	//		if (lf.size()==0)
	//			return null;
	//		fst.dna = sb.toString();
	//		return (lf.toArray(new Fasta[0]));
	//	}
		public static Fasta[] listToFasta(List <String> ls){
			return Fasta.listToFastaList(ls).toArray(new Fasta[0]);
		}
}
