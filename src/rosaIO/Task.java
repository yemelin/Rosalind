package rosaIO;

public class Task {

	private String rId;
	private  String getInputPath (String[] args) {
		String ret = null;
		if (args.length>0) {
			if (!args[0].equals("std"))
				ret = Streams.DATAPATH+args[0];
		}
		else 
			ret = Streams.DATAPATH+getDefaultInputPath();
		return ret;
	}
	
	private String getDefaultInputPath() {
		return "rosalind_"+rId+".txt";
	}

	private  String getOutputPath (String[] args) {
		String ret = null;
		if (args.length>1) {
			if (!args[1].equals("std"))
				ret = Streams.OUTPUTPATH+args[1];
		}
		else 
			ret = Streams.OUTPUTPATH+getDefaultOutputPath();
		return ret;
	}

	private String getDefaultOutputPath() {
		return "rosalind_"+rId+"_out.txt";
	}

	public Task(String rId, String[] args) {
		this.rId = rId;
		scanner = new RosaScanner(Streams.obtainInputStream(getInputPath(args)));
		printer = new RosaPrinter(Streams.obtainOutputStream(getOutputPath(args)));

	}
	public RosaScanner scanner;
	public RosaPrinter printer;
	public void close() {
		scanner.close();
		printer.close();
	}
}
