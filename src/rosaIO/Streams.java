package rosaIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Streams {

	public static InputStream obtainInputStream (String[] args) {
			InputStream is=null;
			if (args.length>0) {
				try {
					is = new FileInputStream (new File(Streams.DATAPATH+args[0]));
				}
				catch (FileNotFoundException e) {
					System.out.println(Streams.DATAPATH+args[0]+ "not found");
	//				System.exit(1);
				}
			}
			else
				is = System.in;
			return is;
		}

	public static OutputStream obtainOutputStream (String[] args) {
		OutputStream os=null;
		if (args.length>1) {
			try {
				os = new FileOutputStream (new File(Streams.OUTPUTPATH+args[1]));
			}
			catch (FileNotFoundException e) {
				System.out.println("Can't write to "+Streams.OUTPUTPATH+args[1]);
//				System.exit(1);
			}
		}
		else
			os = System.out;
		return os;
	}

	//	public static String DATAPATH = "/home/geovvy/workspace/Rosalind/data/";
	public static final String DATAPATH = "/home/vvy/git/rosalind/data/";
	public static final String TABLEPATH = "/home/vvy/git/rosalind/tables/";
	public static final String OUTPUTPATH = "/home/vvy/git/rosalind/output/";

}
