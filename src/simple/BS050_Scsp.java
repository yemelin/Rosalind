//http://rosalind.info/problems/scsp/
package simple;

import rosaIO.Task;
import util.RosaArrays;

// TODO: consider getting rid of duplicated code handling each of two strings
public class BS050_Scsp {
	public static String scsp (String s1, String s2) {
		String cs = RosaArrays.lcs(s1,s2);
		StringBuffer sb = new StringBuffer();
		int pos1=0, pos2=0, posCs=0;
		while (posCs<cs.length()) {
			while (s1.charAt(pos1)!=cs.charAt(posCs) && pos1<s1.length())
				sb.append(s1.charAt(pos1++));//add all non-common
				pos1++;	//skip one common char
			while (s2.charAt(pos2)!=cs.charAt(posCs)&& pos2<s2.length())
				sb.append(s2.charAt(pos2++));
				pos2++;
			sb.append(cs.charAt(posCs++));//next char in the common subsequence
		}
		sb.append(s1.substring(pos1));//append the chars behind the last common
		sb.append(s2.substring(pos2));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Task io = new Task("scsp", args);
		String s1 = io.scanner.readLine();
		String s2 = io.scanner.readLine();
		io.printer.println(RosaArrays.lcs(s1, s2));
		io.printer.println(scsp(s1, s2));
		io.close();
	}

}
