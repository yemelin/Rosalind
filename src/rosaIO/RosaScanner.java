package rosaIO;
//TODO: check the project on the problems that may be caused by:
//accepting empty lines by readLine()
//readInt followed by readLine (reads tail (often just \n), not the next line)
//readArray instead of "readlnArray" (i.e. we need two int arrays, which
//are given as two subsequent input lines)
//TODO: what should be the action if an empty line is read when we need an array?
//fatal error or skip?
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RosaScanner {
	private Scanner sc;
	public RosaScanner(InputStream is){
		sc = new Scanner(is);
	}
	public RosaScanner(String s){
		sc = new Scanner(s);
	}
	public final void close() {
		sc.close();
	}
// I had to add it, because sometimes we need to read till all lines and empty
// line is an OK read 
	public final boolean hasNextLine() {
		return sc.hasNextLine();
	}
//	accepts empty lines!
	public String readLine() {
		if (!sc.hasNext())
			Error.perrorExit();
		return sc.nextLine();
	}
	public List <String> readList() {
		List <String> ls = new ArrayList <String>();
		while (sc.hasNextLine())
			ls.add(sc.nextLine());
		if (ls.size()==0)
			Error.perrorExit();
		return ls;
	}
	public LinkedList<Fasta> readFastaList() {
		LinkedList<Fasta> ls;
		if ((ls = Fasta.listToFastaList(readList()))==null)
			Error.perrorExit();
		return ls;
	}
	public Fasta[] readFastaArray() {
		return readFastaList().toArray(new Fasta[0]);
	}
	public int readInt() {
		if (!sc.hasNextInt())
			Error.perrorExit();
		return sc.nextInt();
	}
	
	public int[] readLineToIntArray() {
		return new RosaScanner(this.readLine()).readIntArray();
	}
	public int[] readIntArray () {
		return (int[])fillArray(new IntReader());
	}
	public double[] readDoubleArray () {
		return (double[])fillArray(new DoubleReader());
	}
	public int[] readSet() {
		sc.useDelimiter("[\\s,{} ]+");
		int[] ret = (int[])fillArray(new IntReader());
		sc.reset();
		return ret;
	}

	private Object fillArray(Reader r) {
		while (r.hasNext()){
			r.add();
		}
		if (r.pos==0)	//not quite good
			Error.perrorExit();
		return r.get();
	}
	private abstract class Reader {
		public final int INITSIZE = 1000;//maybe should belong ArrayReader
		Object a = alloc(INITSIZE);
		int pos=0;
		abstract Object alloc(int capacity);
		abstract boolean hasNext();
		abstract int length();
		abstract void readNext();
		public void realloc(int NewCapacity) {
			Object tmp = alloc(NewCapacity);
			System.arraycopy(a, 0, tmp, 0, pos);
			a = tmp;
		}
		public void add() {
			if (pos == length())
				realloc(pos<<1);
			readNext();
		}
		public Object get() {
			realloc(pos);//shrink
			return a;
		}
	}

	private class IntReader extends Reader{
		Object alloc(int capacity) {
			return new int[capacity];
		}
		public boolean hasNext() {
			return sc.hasNextInt();
		}
		public int length() {
			return ((int[])a).length;
		}
		public void readNext() {
			((int[])a)[pos++] = sc.nextInt();
		}
	}
	private class DoubleReader extends Reader{
		Object alloc(int capacity) {
			return new double[capacity];
		}
		public boolean hasNext() {
			return sc.hasNextDouble();
		}
		public int length() {
			return ((double[])a).length;
		}
		public void readNext() {
			((double[])a)[pos++] = sc.nextDouble();
		}
	}
}
