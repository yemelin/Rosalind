//http://rosalind.info/problems/rna/
package simple;

import rosaIO.Task;
public class BS002_Rna {
//	training, java has a built-in replace() function
	public static String manualReplace(String s, char oldchar, char newchar) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i)==oldchar)
				sb.setCharAt(i, newchar);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Task io = new Task("rna", args);
		String s = io.scanner.readLine();
		System.out.println(s.replace('T', 'U'));	//built-in java function
		io.printer.println(manualReplace(s, 'T', 'U'));	
		io.close();
	}
}
