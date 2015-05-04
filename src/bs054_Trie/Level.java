package bs054_Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
//	static String allwords[] = {"test", "tesla","pig","abacus","anti",}; 
	static String allwords[] = {"ATAGA", "ATC","GAT"};
	Level() {
		nodes = new ArrayList<NodeWithList>();
	}
	static int depth=0;
	String classId;
	public class NodeWithList {
		Node node;
		ArrayList <String> words;
//		String [] words;
		NodeWithList (Node node, ArrayList<String> words) {
//		NodeWithList (Node node, String[] words) {
			this.node = node;
			this.words = words;
		}
		
		public void spawn(Level nextL) {
			HashMap <Character, ArrayList<String>> buckets = 
					new HashMap<>();
			for (String word : words) {
				if (depth<word.length()) {
					char key = word.charAt(depth);
//					System.out.println("depth="+depth+" key="+key);
					if (!buckets.containsKey(key))
						buckets.put(key, new ArrayList<String>());
					buckets.get(key).add(word);
				}
			}
//			System.out.println("words added to hashmap");
			for (char key:buckets.keySet()) {
				Node newnode = new Node(key);
				node.addNode(newnode);
				nextL.addNodeWithList(
						nextL.new NodeWithList(newnode, buckets.get(key)/*.toArray(new String[0])*/));
			}
		}
	}
	ArrayList<NodeWithList> nodes;
	public void addNodeWithList(NodeWithList node) {
		nodes.add(node);
	}
}
