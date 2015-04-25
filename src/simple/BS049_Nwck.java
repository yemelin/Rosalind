package simple;

// TODO: cleanup!!!
import java.io.InputStream;
import java.util.Scanner;

import rosaIO.RosaIO;

public class BS049_Nwck {

	public static int countDistance (String node1, String node2, String newick) {
		int ret;
		int pos1 = newick.indexOf(node1);
		int pos2 = newick.indexOf(node2);
		if (pos1>pos2) {//swap
			int tmp = pos1; pos1=pos2; pos2 = tmp;
		}
		int countNode1Parens = countMatchingParens(newick, pos1, 0, pos2-1);
		int countNode2Parens = countMatchingParens(newick, pos2, pos1+1, newick.length()-1);
		ret = countMatchingParens(newick, pos1, 0, pos2-1) + 
				countMatchingParens(newick, pos2, pos1+1, newick.length()-1);
		int leftParensPos = getNthLeftParens(newick, pos2, countNode2Parens)-1;
//		System.out.println(newick.substring(leftParensPos-1, leftParensPos+50));
//		System.out.println(countNode1Parens+" "+countNode2Parens);
//		System.out.println(getParenthesizedPart(newick, pos1, countNode1Parens));
//		System.out.println(getParenthesizedPart(newick, pos2, countNode2Parens));
		if (newick.charAt(leftParensPos)==',') {
//			System.out.println("BINGO");
			ret+=2;
		}
		return ret;
	}
//	A hack. Fix it.
	private static int getNthLeftParens(String s, int pos, int n) {
		while (n>0) {
			if (s.charAt(--pos)=='(')
				n--;
			else if (s.charAt(pos)==')')
				n++;
		}
		return pos;
	}
	private static int getNthRightParens(String s, int pos, int n) {
		while (n>0) {
			if (s.charAt(++pos)==')')
				n--;
			else if (s.charAt(pos)=='(')
			n++;
		}
		return pos;
	}
	private static String getParenthesizedPart(String s, int pos, int n) {
		int left = getNthLeftParens(s, pos, n);
		int right = getNthRightParens(s, pos, n);
//		System.out.println(left+" right:"+right+"n="+n);
		return s.substring(left, right+1);
	}
	
	private static int countUnmatchedLeftParens(String s, int lo, int hi){
		int ret = 0;
		for (int i=lo; i<=hi;i++) {
			if (s.charAt(i)=='(')
				ret++;
			else if (s.charAt(i)==')')
				if (ret>0)
					ret--;
		}
		return ret;
	}

	private static int countUnmatchedRightParens(String s, int lo, int hi){
		int ret = 0;
		for (int i=hi; i>=lo;i--) {
			if (s.charAt(i)==')')
				ret++;
			else if (s.charAt(i)=='(')
				if (ret>0)
					ret--;
		}
		return ret;		
	}
	
	private static int countMatchingParens(String newick, int pos, int lo, int hi) {
		
		int countLeft = countUnmatchedLeftParens(newick, lo, pos);
		int countRight = countUnmatchedRightParens(newick, pos, hi);
		int ret = (countLeft>countRight)? countRight:countLeft;
//		System.out.println(newick.substring(lo, hi+1));
//		System.out.println(countLeft+" "+countRight+" "+pos+" "+lo+" "+hi+" "+ret);
		return ret;
	}
	
	public static void main(String[] args) {
//		String newick = "(((ab)(c))def)";
//		System.out.println(countMatchingParens(newick, 1, 4, newick.length()-1));
//		String newick = "(Ahaetulla_cachinans,Alauda_flavirufa,Alloporus_rufus,Anser_leptochelis,Aplopeltura_ruthveni,Apodora_alpina,Apodora_cinclus,Argynnis_colombianus,Aythya_verrucosus,Bombus_chrysargos,Bombycilla_odoratus,Brachyramphus_interpres,Bradypodion_bengalensis,Bronchocela_arenarius,Carabus_quinquestriatus,Ceratophrys_horridum,Certhia_amboinensis,Chamaeleo_wogura,Chelodina_fallax,Chlamydotis_mitratus,Chondropython_aegyptia,Chondropython_monacha,Chrttusia_lobatus,Chrysemys_collybitus,Circaetus_orientalis,Citharacanthus_lutris,Coleonyx_rubida,Corvus_mackloti,Crotaphytus_yeltoniensis,Cuculus_brevipes,Cuculus_fissipes,Cyclemys_getula,Cygnopsis_cliffordii,Dahurinaia_davidiana,Dendrobates_erythronotus,Equus_relictus,Eremophila_glacialis,Eucratoscelus_aestivus,Falcipennis_sebae,Fulica_angulifer,Gecarcinus_circia,Glareola_porphyrio,Grus_maurus,Hadogenes_brandtii,Hadogenes_grupus,Haliaetus_czerskii,Haplopelma_pusilla,Hemitheconyx_fuscus,Hemitheconyx_gebleri,Homalopsis_leucostomum,Iguana_perrotetii,Larus_boa,Lepidobatrachus_carnivorus,Leuciscus_altaica,Leuciscus_glacialis,Megaptera_plumipes,Mesoplodon_docilis,Micropalama_vastus,Motacilla_edulis,Motacilla_melanoleucus,Nyctixalus_cygnus,Oceanodroma_bairdi,Oligodon_himantopus,Otis_diffidens,Oxyura_giganteus,Pandion_hyemalis,Pandion_rhymnus,Parnassius_boschas,Parus_circia,Passer_agama,Passer_rubida,Philomachus_insignis,Phormictopus_pygmeus,Phrynocephalus_sauromates,Phrynomerus_australis,Picus_fuscatus,Plegadis_chinensis,Procellaria_ussuriensis,Rhacophorus_aceras,Rhamphiophis_acutus,Rhinolophus_campestris,Riparia_grupus,Salvelinus_coelestis,Scaphiophryne_calvus,Siniperca_americanus,Sorex_falcipennis,Sphenops_lasiopterus,Squaterola_saiga,Strepsilas_amethistina,Synthliboramphus_gallicus,Tadarida_pulchra,Teratolepis_fiber,Teratolepis_hendricksoni,Terpsihone_vertebralis,Thecla_dolosus,Uroplatus_gordoni,Vipera_cynodon)Acanthis_squaterola;";
//		String node1="Cuculus_fissipes"; String node2="Circaetus_orientalis";
		InputStream is = RosaIO.obtainInputStream(args);
		Scanner sc = new Scanner(is);
		String newick="";
		String nodes_string="";
		do {
			while (sc.hasNextLine() && (newick = sc.nextLine()).equals(""));
			while (sc.hasNextLine() && (nodes_string = sc.nextLine()).equals(""));
			if (!nodes_string.equals("") && !newick.equals("")) {
//				System.out.println(newick);
				String[] nodes = nodes_string.split("\\s");
//				System.out.println("Got next data. Graph: "+newick);
//				System.out.println("Nodes:"+nodes[0]+" "+nodes[1]);
				System.out.print(countDistance(nodes[0], nodes[1], newick)+" ");
			}
		} while (sc.hasNextLine());
		System.out.println();
	}
}
