package bs032_Tree;
//http://rosalind.info/problems/tree/
//TODO: cleanup
//TODO: consider MatrixReader inner class for RosaScanner, that would read
//arrays of arrays, like in this task
import java.util.ArrayList;
import rosaIO.Task;

public class BS032_Tree {

	
	public static void main(String[] args) {
		Task io = new Task("tree", args);
		int size = io.scanner.readInt();
		System.out.println(size);
		io.scanner.readLine();
		ArrayList <int[]> edges = new ArrayList<int[]>();		
		int tmp[];
		do {
			tmp = io.scanner.readLineToIntArray();
			if (tmp.length==2)
				edges.add(tmp);
		} while (tmp.length==2 && (io.scanner.hasNextLine()));
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
		io.printer.println(gi.getSize()-gi.getNumEdges()-1);
	}
}
