package bs054_Trie;

import java.util.ArrayList;
import java.util.HashMap;
// Level is an auxiliary class for building a trie. We build it level by level:
//					a   --> level
//				   / \
//				  b   c --> level
public class Level {
//	static String allwords[] = {"test", "tesla","pig","abacus","anti",}; 
//	static String allwords[] = {"ATAGA", "ATC","GAT"};
	Level() {
		nodes = new ArrayList<NodeWithList>();
	}
	static int depth=0;
	String classId;

//	an auxiliary inner class for building a trie. It holds not only the normal
//	node of the trie, but also all the words the "go through" this node
	public class NodeWithList {
		Node node;
		ArrayList <String> words;
//		String [] words;
		NodeWithList (Node node, ArrayList<String> words) {
//		NodeWithList (Node node, String[] words) {
			this.node = node;
			this.words = words;
		}
// working routine. NodeWithList spawns itself to the next level as follows:
//	all the words of this node are divided into buckets according to their
//	next letters, then new nodes are build based on these buckets, and
//	constitute the next level
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
