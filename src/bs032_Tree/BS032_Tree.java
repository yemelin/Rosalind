package bs032_Tree;
//http://rosalind.info/problems/tree/
//TODO: cleanup
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import rosaIO.RosaIO;

public class BS032_Tree {

	
	public static void main(String[] args) throws IOException {
		int size = 0;
		InputStream is = new FileInputStream (new File(RosaIO.DATAPATH+args[0]));
		System.out.println("Opened "+RosaIO.DATAPATH+args[0]);
		ArrayList <int[]> edges = new ArrayList<int[]>();
		Scanner sc = new Scanner(is);
		size = sc.nextInt();
		int i = 0;
		do {
			int [] tmp = new int[2];
			for (i=0; i<2;i++)
				if (sc.hasNextInt()) {
					tmp[i] = sc.nextInt();
				}
				else break;
			if (i==2) {
				edges.add(tmp);
			}
		} while (i==2);
		System.out.println("Got input. Graph size: "+size);
//		for (i=0; i<edges.size(); i++)
//			RosaIO.printArray(edges.get(i));
//		int [][] a = ((int[][])(edges.toArray()));
//		for (i=0; i<edges.size(); i++)
			int n1 = ((int [])((edges.toArray())[0]))[0];
			int n2 = ((int [])((edges.toArray())[0]))[1];
			System.out.println(n1 +" "+n2);
		int [][] a = new int[edges.size()][2];
		edges.toArray(a);
//		for (int j = 0; j < a.length; j++) {
//			RosaIO.printArray(a[j]);
//		}
		GraphInfo gi = new GraphInfo(size, a);
// if we reached here, graph is at least a forest (set of trees). We get the tree
//	connecting the subtrees, and connected graph is a tree when and only when
//	Number_Of_Nodes = Number_Of_Edges+1
		System.out.println("Edges required to form a tree: "+ (gi.getSize()-gi.getNumEdges()-1));
	}
}
