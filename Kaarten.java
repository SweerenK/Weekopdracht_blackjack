package weekopdracht.v3;

import weekopdracht.v2.Kaartendeck;

public class Kaarten {
	String kaartsoort, kaartwaarde;
	int kaartRekenWaarde;
	
	/*
	void verdeelKaarten(Kaartendeck stapel){
		int aantalSpelers = Spel.aantalSpelers;
		
		for(int w = 0; w < aantalSpelers; w++) {
			Spel.alleSpelers.get(w).gekregenKaarten.add(stapel.geefKaart());
			Spel.stapel.verwijderBovensteKaart();
			System.out.println(Spel.alleSpelers.get(w) + " heeft kaarten " + Spel.alleSpelers.get(w).gekregenKaarten);
		}
		
	}
	*/
	
	
	@Override
	public String toString() {
		return kaartsoort + " " + kaartwaarde + " (" + kaartRekenWaarde + ")";
	}
}
