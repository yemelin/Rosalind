package simple;
import rosaIO.RosaIO;
import util.InOut;
import util.RosaArrays;
public class BS024_Lgis {
	public static void main(String[] args) {
		int n = InOut.readInt(1)[0];
		int a[] = InOut.readInt(n);
//		RosaIO.printArray(a);
//		
//		RosaIO.printArray(RosaArrays.longestSeq(a, true));
//		RosaIO.printArray(RosaArrays.longestSeq(a, false));
//		
		RosaIO.printArray(RosaArrays.longestSeq(a, true));
		RosaIO.printArray(RosaArrays.longestSeq(a, false));
		
	}
}
