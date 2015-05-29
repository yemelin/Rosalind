package simple;

import rosaIO.Task;

public class BS009_Subs {
	
	private static int[] indexesOf(String st, String pattern) {
		int pos = -1, i=0;
		int tmp[] = new int[st.length()];
		while ((pos = st.indexOf(pattern, pos+1))!=-1)
			tmp[i++] = pos+1;	//1-based numbering
		int[] ret = new int[i];
		System.arraycopy(tmp, 0, ret, 0, i);
		return ret;
	}

	public static void main(String[] args) {
		Task io = new Task("subs",args);
		String st = io.scanner.readLine();
		String pattern = io.scanner.readLine();
		io.printer.printArray(indexesOf(st, pattern));
		io.close();
	}


}
