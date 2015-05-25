package bs056_Ctbl;

import java.util.ArrayList;
import java.util.Scanner;

import rosaIO.Streams;
import rosaIO.Task;

public class BS056_Ctbl {
	public static ArrayList<int[]> allNodes = new ArrayList<>();
	public static ArrayList<Word> words = new ArrayList<>();

	public static int pos=0;
	public static void getAllNodes (String nwck) {
		int start = pos;
		while (nwck.charAt(++pos)!=')') {
			if (nwck.charAt(pos)=='(')
				getAllNodes(nwck);
		}
		int tmp[] = {start, pos};
		allNodes.add(tmp);
	}

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
//		String s = "(dog,((elephant,mouse),robot),cat);";
		String path1 = Streams.OUTPUTPATH+args[1];
		String path2 = "/home/vvy/git/RosaPy/output/056_CTBL.txt";
		Task io = new Task("ctbl",args);
		String s = io.scanner.readLine();
//		System.out.println(s);
		getAllNodes(s);
//		for (int[] lims : allNodes) {
//			System.out.println(s.substring(lims[0], lims[1]+1));
//		}
		splitToWords(s);
//		for (Word word: words)
//			System.out.println(word.w +" "+word.start+" "+word.end);
		Scanner sc = new Scanner(System.in);
		
		for (int i=allNodes.size()-2; i>=0; i--) {
			String out = "";
//			System.out.println("markUp for "+allNodes.get(i)[0]+" "+allNodes.get(i)[1]);
//			System.out.println(s.substring(allNodes.get(i)[0],allNodes.get(i)[1]+1));
			doMarkUp(allNodes.get(i)[0],allNodes.get(i)[1]);
			for (Word w : words) {
				out+=(w.markUp);
//				io.printer.print(w.markUp);
			}
			io.printer.println(out);
			if (out.equals("000000000000000000000000000000000000000000000000000000000000000000000000001010000000000"))
				System.out.println(s.substring(allNodes.get(i)[0],allNodes.get(i)[1]+1));
//			System.out.println(out);
//			sc.nextLine();
		}
		Util.compareContent(path1, path2);
	}
}
