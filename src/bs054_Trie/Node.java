package bs054_Trie;

import java.io.PrintStream;
import java.util.ArrayList;

public class Node {
	static int counter = 1;
	int id;
	char inEdge;
	ArrayList<Node> nextNodes;
	Node (char inEdge) {
		this.id = counter++;
		this.inEdge=inEdge;
		nextNodes = new ArrayList<>();
	}
	public void addNode(Node node) {
		nextNodes.add(node);
	}
	public void printGraph(){
		for (Node node: nextNodes) {
			System.out.println(id+" "+node.id+" "+node.inEdge);
			node.printGraph();
		}
	}
	public void printGraphNonRec(PrintStream ps) {
		ArrayList<Node> level = new ArrayList<>();
		level.add(this);
		while (!level.isEmpty()) {
			ArrayList<Node> next = new ArrayList<>();
			for (Node node:level) {
				next.addAll(node.nextNodes);
				for (Node nextNode:node.nextNodes)
					ps.println(node.id+" "+nextNode.id+" "+nextNode.inEdge);
			}
			level = next;
		}
	}
}
