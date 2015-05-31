package bs056_Ctbl;

import java.util.ArrayList;
import rosaIO.Task;

public class BS056_Ctbl {
	public static ArrayList<int[]> allNodes = new ArrayList<>();
	public static ArrayList<Word> words = new ArrayList<>();
	private static int pos=0;

// get all non-trivial nodes (i.e. not leaves). Each non-leaf node is a subtree,
//	and it's just whatever comes between two matching parenthesis. The whole
//	tree is also added the last, and should be later skipped
//	Nodes are stored as their respective parentheses positions 
	public static void getAllNodes (String nwck) {
		int start = pos;	//global var make simplifies the recursion
		while (nwck.charAt(++pos)!=')') {
			if (nwck.charAt(pos)=='(')
				getAllNodes(nwck);
		}
		int tmp[] = {start, pos};
		allNodes.add(tmp);
	}

// class stores the word's positions, so that later we could determine, whther
//	it is between parenthesis (and belongs to the corresponding sub-tree)
//	or not
	public static class Word implements Comparable<Word>{
		String w;
		int start;
		int end;
		int markUp;
		Word (String t, int i, int j) {
			w = t; start = i; end = j;
		}
		
		@Override
		public int compareTo(Word wrd) {
			return w.compareTo(wrd.w);
		}
	}

//	self-made split instead of built-in, because we need to store the absolute
//	positions of the words in Newick, and we get the them in the process of splitting to words
	public static void splitToWords(String nwck) {
		boolean inWord = false;
		String sep = "(,)";
		int end = 0;
		int start = 0;
		while (end<nwck.length()) {
			char c = nwck.charAt(end); 
			if (sep.indexOf(c)!=-1 && inWord) {
				words.add(new Word(nwck.substring(start, end), start, end));
				inWord = false;
			}
			else if (sep.indexOf(c)==-1 && !inWord) {
				inWord = true;
				start = end;
			}
			end++;
		}
		words.sort(null);
	}
	
	public static void doMarkUp(int start, int end) {
		for (Word w : words) {
			if (w.start>=start && w.end<=end)
				w.markUp = 1;
			else
				w.markUp = 0;
		}
	}

	public static void main(String[] args) {
		Task io = new Task("ctbl",args);
		String s = io.scanner.readLine();
		getAllNodes(s);
		splitToWords(s);
		
		for (int i=allNodes.size()-2; i>=0; i--) {
			String out = "";
			doMarkUp(allNodes.get(i)[0],allNodes.get(i)[1]);
			for (Word w : words) {
				out+=(w.markUp);
//				io.printer.print(w.markUp);
			}
			io.printer.println(out);
		}
	}
}
