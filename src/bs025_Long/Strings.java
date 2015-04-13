package bs025_Long;

import java.util.List;

public class Strings {

	public static String merge (List<String> ls) {
		String head = "";
		int overlap = 0;
//		------------
		do {
		for (int i=1; i<ls.size(); i++) {
//			if (areMergeable(ls.get(0),ls.get(i))) {
			if ((overlap = getOverlap(ls.get(0),ls.get(i)))>0) {
//				ls.set(0, ls.get(0) + ls.get(i)); //merge
				ls.set(0, merge(ls.get(0),ls.get(i), overlap));
				ls.remove(i);
				i = 0; //reset
			}
		}
//		if (areMergeable(ls.get(0), head)) {
		if ((overlap = getOverlap(ls.get(0),head))>0 ||
				head.length()==0) {
//			head = ls.remove(0)+head;
			head = merge(ls.remove(0), head, overlap);
//			System.out.println("current head is: " + head);
		}
		else {
			System.out.println("Can't merge all the strings");
			return head;
		}
		}
		while (ls.size()>0);
//		------------
		return head;
	}
//	TODO: calculate minOverlap or look for optimal match
	private static int minOverlap = 500;
	private static int getOverlap (String s1, String s2) {
		
		int maxOverlap = (s1.length()>s2.length())? s2.length(): s1.length();
		for (int i=maxOverlap; i>= minOverlap; i--)
			if (s1.endsWith(s2.substring(0, i)))
				return i;
		return 0;
	}
	
//	private static boolean areMergeable(String s1, String s2) {
//		boolean ret =(s1.length()==0) || (s2.length()==0) ||
//				(s2.charAt(0) == s1.charAt(s1.length()-1));
//		System.out.println("testing "+s1+" and "+s2);
//		return ret;
//	}
//	
	private static String merge(String s1, String s2, int overlap ) {
//		System.out.println("merging "+ s1+ ", "+s2+": "+s1+s2.substring(overlap));
		return s1+s2.substring(overlap);
	}
//	public static void main(String[] args) {
//		List <String> ls = RosaIO.getAllInputToList(args);
//		for (Iterator<String> iterator = ls.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			System.out.println(string);
//		}
//		merge(ls);
////		System.out.println(getOverlap("abcddefabcdef", "defabcdef"));
//	}
}
