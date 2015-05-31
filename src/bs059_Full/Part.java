package bs059_Full;

import java.util.ArrayList;
import java.util.List;

import util.MassTable;
import util.Rstring;

// part of the peptide, corresponding to one of the sum weights given
public class Part {
	public Part(int number, double total_weight) {
		id = number;
		weight = total_weight;
	}
	
	public int id;
	public boolean used = false;
	public double weight;
	public Part prev;
// list of possible next Parts, that match some protein by weights difference	
	public List<Part> nextParts = new ArrayList<>();
	int _next = 0;
//	public char nextProt;
	List<Character> nextProts = new ArrayList<>();
	public void tryAddNext(Part p) {
		char tmpProt = MassTable.getProtByMass(p.weight - weight, Rstring.PRECISION);
		if (tmpProt!=(char)-1) {
			nextParts.add(p);
			nextProts.add(tmpProt);
		}
	}
	public boolean hasNext() {
		return _next<nextParts.size();
	}
	public Part next() {
		return nextParts.get(_next++);
	}
}
