package bs056_Ctbl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
// an auxiliary class for comparison of the content of two classes with 
// arbitrary order of desirably the same set of strings
// Not used in the task
public class Util {

	public static List<String> readFileToList (String path, Charset encoding){
		System.out.println("trying to read "+path);
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

	public static boolean compareContent (String path1, String path2) {
		List <String> fs1 = readFileToList(path1, Charset.defaultCharset());
		List <String> fs2 = readFileToList(path2, Charset.defaultCharset());
		fs1.sort(null); fs2.sort(null);
		for (int i=0; i<fs1.size(); i++) {
			if (!fs1.get(i).equals(fs2.get(i))) {
				System.out.println(fs1.get(i));
				System.out.println(fs2.get(i));
				System.exit(1);
			}
		}
		System.out.println("Content equal");
		return true;		
	}
}
