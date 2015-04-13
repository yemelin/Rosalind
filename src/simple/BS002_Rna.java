package simple;
import rosaIO.RosaIO;
public class BS002_Rna {
	public static String manualReplace(String s, char oldchar, char newchar) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i)==oldchar)
				sb.setCharAt(i, newchar);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		String s=RosaIO.getAllInput(args);
		if (s!=null) {
			System.out.println(s.replace('T', 'U'));
			System.out.println(manualReplace(s, 'T', 'U'));
		}	
	}
}
