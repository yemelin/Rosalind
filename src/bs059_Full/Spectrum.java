package bs059_Full;

import rosaIO.Rstring;

public class Spectrum {
	Part[] parts;
	double[] weights;
	char tmpProt;
	public void fillParts() {
		parts = new Part[weights.length];
		for (int i=0; i<parts.length; i++) {
			parts[i] = new Part(i, weights[i]);
			for (int j=i-1; j>=0; j--) {
//				double mass = parts[i].weight - parts[j].weight;
//				if ((tmpProt=Rstring.getProtByMass(mass, Rstring.PRECISION))!=((char)-1)) {
//					System.out.println("added to "+j+": "+mass+" "+tmpProt);
//					parts[j].nextParts.add(parts[i]);
//					parts[j].nextProt = tmpProt;
//				}
				parts[j].tryAddNext(parts[i]);
			}
		}		
	}
}
