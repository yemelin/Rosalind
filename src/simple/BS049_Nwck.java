package simple;

// The distance is counted as the sum of all unmatched parenthesis between
// the nodes, comma between adds +2, because it means some of their subtrees
// are attached to the same node, e.g (cat,dog) = 2
// all matched parentheses mean the subtrees, that are not between this nodes

// TODO: cleanup!!!
import rosaIO.Task;

public class BS049_Nwck {

	public static int countDistance (String node1, String node2, String newick) {
		int ret;
		int pos1 = newick.indexOf(node1);
		int pos2 = newick.indexOf(node2);
		if (pos1>pos2) {//swap
			int tmp = pos1; pos1=pos2; pos2 = tmp;
		}
//		int countNode1Parens = countMatchingParens(newick, pos1, 0, pos2-1);
		int countNode2Parens = countMatchingParens(newick, pos2, pos1+1, newick.length()-1);
		ret = countMatchingParens(newick, pos1, 0, pos2-1) + 
				countMatchingParens(newick, pos2, pos1+1, newick.length()-1);
		int leftParensPos = getNthLeftParens(newick, pos2, countNode2Parens)-1;
		if (newick.charAt(leftParensPos)==',') {
			ret+=2;
		}
		return ret;
	}
// TODO: A hack. Fix it.
	private static int getNthLeftParens(String s, int pos, int n) {
		while (n>0) {
			if (s.charAt(--pos)=='(')
				n--;
			else if (s.charAt(pos)==')')
				n++;
		}
		return pos;
	}
//	private static int getNthRightParens(String s, int pos, int n) {
//		while (n>0) {
//			if (s.charAt(++pos)==')')
//				n--;
//			else if (s.charAt(pos)=='(')
//			n++;
//		}
//		return pos;
//	}
//	private static String getParenthesizedPart(String s, int pos, int n) {
//		int left = getNthLeftParens(s, pos, n);
//		int right = getNthRightParens(s, pos, n);
//		return s.substring(left, right+1);
//	}
	
	private static int countUnmatchedLeftParens(String s, int lo, int hi){
		int ret = 0;
		for (int i=lo; i<=hi;i++) {
			if (s.charAt(i)=='(')
				ret++;
			else if (s.charAt(i)==')')
				if (ret>0)
					ret--;
		}
		return ret;
	}

	private static int countUnmatchedRightParens(String s, int lo, int hi){
		int ret = 0;
		for (int i=hi; i>=lo;i--) {
			if (s.charAt(i)==')')
				ret++;
			else if (s.charAt(i)=='(')
				if (ret>0)
					ret--;
		}
		return ret;		
	}
	
	private static int countMatchingParens(String newick, int pos, int lo, int hi) {
		
		int countLeft = countUnmatchedLeftParens(newick, lo, pos);
		int countRight = countUnmatchedRightParens(newick, pos, hi);
		int ret = (countLeft>countRight)? countRight:countLeft;
		return ret;
	}
	
	public static void main(String[] args) {
		Task io = new Task("nwck", args);
		String newick="";
		String nodes_string="";
		do {
			newick = io.scanner.readLine();
			nodes_string = io.scanner.readLine();
			if (io.scanner.hasNextLine())
				io.scanner.readLine(); //there must be an empty line separator
			if (!nodes_string.equals("") && !newick.equals("")) {
				String[] nodes = nodes_string.split("\\s");
				io.printer.print(countDistance(nodes[0], nodes[1], newick)+" ");
			}
		} while (io.scanner.hasNextLine());
		io.printer.println();
	}
}
