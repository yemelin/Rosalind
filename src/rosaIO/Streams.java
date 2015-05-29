package rosaIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
// TODO: handle inconsistency. Streams should be opened just with a String,
// not args. Choice should be in the separate function
public class Streams {
	
	public static final String DATAPATH = "/home/vvy/git/rosalind/data/";
	public static final String TABLEPATH = "/home/vvy/git/rosalind/tables/";
	public static final String OUTPUTPATH = "/home/vvy/git/rosalind/output/";

	public static InputStream obtainInputStream (String[] args) {
			InputStream is=null;
			if (args.length>0) {
				try {
					is = new FileInputStream (new File(Streams.DATAPATH+args[0]));
				}
				catch (FileNotFoundException e) {
					System.err.println(Streams.DATAPATH+args[0]+ " not found");
					System.exit(1);
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
				System.err.println("Can't write to "+Streams.OUTPUTPATH+args[1]);
				System.exit(1);
			}
		}
		else
			os = System.out;
		return os;
	}

// inconsistent - other obtainStream take program arguments	
	public static InputStream obtainUrlStream (String url) {
		InputStream is = null;
		try {
			URL website = new URL(url); 
			URLConnection connection = website.openConnection();
			is =  connection.getInputStream();
		}
		catch (IOException e) {
			System.err.println("Can't open url "+url);
			System.exit(1);
		}
		return is;
	}
}
