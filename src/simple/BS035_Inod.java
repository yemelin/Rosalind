package simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import rosaIO.RosaIO;
import util.InOut;

// http://rosalind.info/problems/inod/
public class BS035_Inod {
	public static void main(String[] args) {
		InputStream is=null;
		if (args.length>0) {
			try {
				is = new FileInputStream (new File(RosaIO.DATAPATH+args[0]));
			}
			catch (FileNotFoundException e) {
				System.out.println(RosaIO.DATAPATH+args[0]+ "not found");
				System.exit(1);
			}			
		}
		else is = System.in;
		int n[] = InOut.readInt(is, 1);
//	For the unrooted binary tree turning a leaf into an internal node (by adding two leaves)
//	results in +1 to internal nodes and +1 (i.e -1+2) to leaves. Therefore
//	always N_leaves = N_intnodes+2
		if (n!=null)
			System.out.println(n[0]-2);
	}
}
