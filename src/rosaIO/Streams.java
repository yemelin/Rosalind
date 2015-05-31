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
// update Error class to use perror() instead of exit()
public class Streams {
	
//	public static final String DATAPATH = "/home/vvy/git/rosalind/data/";
	public static final String DATAPATH = "./data/";
	public static final String TABLEPATH = "/home/vvy/git/rosalind/tables/";
	public static final String OUTPUTPATH = "/home/vvy/git/rosalind/output/";

	public static InputStream obtainInputStream (String inputPath) {
			InputStream is=null;
			if (inputPath!=null) {
				try {
					is = new FileInputStream (new File(inputPath));
				}
				catch (FileNotFoundException e) {
					System.err.println(System.getProperty("user.dir")+inputPath+ " not found");
					System.exit(1);
				}
			}
			else
				is = System.in;
			return is;
		}

	public static OutputStream obtainOutputStream (String outputPath) {
		OutputStream os=null;
		if (outputPath!=null) {
			try {
				os = new FileOutputStream (new File(outputPath));
			}
			catch (FileNotFoundException e) {
				System.err.println("Can't write to "+outputPath);
				System.exit(1);
			}
		}
		else
			os = System.out;
		return os;
	}

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
