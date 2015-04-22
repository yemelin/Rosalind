package rosaIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RosaIO {
//	public static String DATAPATH = "/home/geovvy/workspace/Rosalind/data/";
	public static String DATAPATH = "/home/vvy/git/rosalind/data/";
	public static String TABLEPATH = "/home/geovvy/workspace/Rosalind/tables/";
	public static final String fname ="rosalind_cons2.txt";
//	copy-pasted from http://stackoverflow.com/questions/326390/
	public static String readFile(String path, Charset encoding) {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		}
		catch (IOException e) {
			return null;
		}
		return new String(encoded, encoding);
	}
	public static String readInput() {
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine())
			sb.append(sc.nextLine());
		sc.close();
		return sb.toString();
	}
	public static List<String> readInputToList() {
		List<String> ls = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine())
			ls.add(sc.nextLine());
		sc.close();
		return ls;

	}
//	------------------------------------------------
//	TODO: handle errors smoother. Perhaps, too many return null statements
//	TODO: split label to actual label and comment
	public static List<String> readFileToList (String path, Charset encoding){
//		System.out.println("trying to read "+path);
//		Path filePath = new File(path).toPath(); 
		Path filePath = Paths.get(path);
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, encoding);
		} catch (IOException e) {
			System.out.println("cant'read");
			return null;
//			e.printStackTrace();
		}
		return stringList;
	}
	
	public static String getAllInput(String args[]) {
		String s;
		if ((args.length>0) && 
			(s = RosaIO.readFile(args[0], Charset.defaultCharset()))!=null)
			;
		else  
			s = RosaIO.readInput();
		return (s=="") ? null:s;

	}
	public static List<String> getAllInputToList(String args[]) {
		List<String> ls;
		if (args.length==0)
			ls = readInputToList();
		else
			ls = readFileToList(RosaIO.DATAPATH+args[0], Charset.defaultCharset());
		return ls;
	}
	public static void printArray (int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i<a.length-1) System.out.print(" ");
		}
		System.out.println();
	}
	public static InputStream obtainInputStream (String[] args) {
			InputStream is=null;
			if (args.length>0) {
				try {
					is = new FileInputStream (new File(DATAPATH+args[0]));
				}
				catch (FileNotFoundException e) {
					System.out.println(DATAPATH+args[0]+ "not found");
	//				System.exit(1);
				}
			}
			else
				is = System.in;
			return is;
		}

}
