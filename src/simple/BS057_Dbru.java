package simple;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rosaIO.Task;
import util.Rstring;

public class BS057_Dbru {
	public static void printEdges (Set<String> edges, PrintStream ps) {
		for (String edge : edges) {
			ps.println("("+edge.substring(0, edge.length()-1)+
					", "+edge.substring(1, edge.length())+")");
		}
	}
	public static void main(String[] args) {
		Task io  = new Task("dbru", args);
		List<String>kmers = io.scanner.readList();
		Set<String>edges = new HashSet<>();
		for (String kmer : kmers) {
			edges.add(kmer);
			edges.add(Rstring.dnaReverseComplement(kmer));
//			System.out.println(kmer+" "+Rstring.dnaReverseComplement(kmer));
		}
		printEdges(edges, io.printer);
	}
}
