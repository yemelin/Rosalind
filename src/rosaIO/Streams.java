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
					is = new FileInputStream (new File(RosaIO.DATAPATH+args[0]));
				}
				catch (FileNotFoundException e) {
					System.out.println(RosaIO.DATAPATH+args[0]+ "not found");
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
				os = new FileOutputStream (new File(RosaIO.OUTPUTPATH+args[1]));
			}
			catch (FileNotFoundException e) {
				System.out.println("Can't write to "+RosaIO.OUTPUTPATH+args[1]);
//				System.exit(1);
			}
		}
		else
			os = System.out;
		return os;
	}

}
