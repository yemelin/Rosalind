package rosaIO;

import java.io.OutputStream;
import java.io.PrintStream;

public class RosaPrinter extends PrintStream{
	public RosaPrinter (OutputStream os) {
		super(os);
		ps = this;
	}
	private PrintStream ps;
//*******Internal adapter printers for the general printing cycle********* 	
//	Perhaps, there was no need for the abstraction. Simple repeating the
//	printing cycle for each type of array would have taken less space
	private abstract class ArrayPrinter {
		String open="";
		String close="";
		String sep=" ";
		abstract int length();
		abstract void print(int i);
	}

	private class IntArrayPrinter extends ArrayPrinter {
		int[] _a; 
		IntArrayPrinter(Object o) {
			_a=(int[])o;
		}
		@Override
		final int length() {
			return _a.length;
		}
		@Override
		final void print(int i) {
			ps.print(_a[i]);
		}
	}
	
	private class DoubleArrayPrinter extends ArrayPrinter {
		double[] _a; 
		DoubleArrayPrinter(Object o) {
			_a=(double[])o;
		}
		@Override
		final int length() {
			return _a.length;
		}
		@Override
		final void print(int i) {
			ps.print(_a[i]);
		}
	}
	private class SetPrinter extends IntArrayPrinter {
		SetPrinter(Object o) {
			super(o);
			open = "{";
			close = "}";
			sep = ", ";
		}		
	}
	private class FastaArrayPrinter extends ArrayPrinter{
		Fasta[] _a;
		FastaArrayPrinter(Object o) {
			_a = (Fasta[])o;
			open = ">";
		}
		@Override
		final int length() {
			return _a.length;
		}
		@Override
		void print(int i) {
			ps.println(_a[i].label);
			ps.print(_a[i].dna);			
		}
	}
	public  void printArray(ArrayPrinter ap, int start, int end) {
		if (end<ap.length()) {
			ps.print(ap.open);
			for (int i = start; i < end+1; i++) {
				ap.print(i);
				if (i<ap.length()-1)
					ps.print(ap.sep);
			}
			ps.println(ap.close);
		}
	}
//	------------------------------------------------
	public void printArray(ArrayPrinter ap) {
		printArray(ap, 0, ap.length()-1);
	}
	public void printArray(int[] a) {
		printArray(new IntArrayPrinter(a));
	}
	public void printArray(double[] a) {
		printArray(new DoubleArrayPrinter(a));
	}
	public void printArray(Fasta[] a) {
		printArray(new FastaArrayPrinter(a));
	}
	public void printSet(int[] a) {
		printArray(new SetPrinter(a));
	}
}
