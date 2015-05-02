package rosaIO;

public class Task {
	public Task(String rId, String[] args) {
		scanner = new RosaScanner(Streams.obtainInputStream(args));
		printer = new RosaPrinter(Streams.obtainOutputStream(args));
	}
	public RosaScanner scanner;
	public RosaPrinter printer;
	public void close() {
		scanner.close();
		printer.close();
	}
}
