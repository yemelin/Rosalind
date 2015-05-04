package bs054_Trie;

import java.util.ArrayList;
import rosaIO.Task;
//TODO: extract building a trie to a separate class and a separate function
public class BS054_Trie {
	public static void main(String[] args) {
		Task io = new Task("trie", args);
		ArrayList<String> allwords = io.scanner.readList();

		Node root = new Node((char)(-1));
		Level l = new Level();
		Level next;
		l.addNodeWithList(l.new NodeWithList(root, allwords));

		while (!l.nodes.isEmpty()) {
			next = new Level();
			for (Level.NodeWithList node: l.nodes) {
				node.spawn(next);
			}
			l = next;
			Level.depth++;
		}
		root.printGraphNonRec(io.printer);
	}
}
