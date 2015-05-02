package simple;

import rosaIO.Task;

// http://rosalind.info/problems/inod/
public class BS035_Inod {
	public static void main(String[] args) {
		Task io = new Task("inod", args);
		int n = io.scanner.readInt();
//	For the unrooted binary tree turning a leaf into an internal node (by adding two leaves)
//	results in +1 to internal nodes and +1 (i.e -1+2) to leaves. Therefore
//	always N_leaves = N_intnodes+2
		io.printer.println(n-2);
		io.close();
	}
}
