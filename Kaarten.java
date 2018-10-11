package weekopdracht.v3;

import weekopdracht.v2.Kaartendeck;

public class Kaarten {
	String kaartsoort, kaartwaarde;
	int kaartRekenWaarde;
	
	@Override
	public String toString() {
		return kaartsoort + " " + kaartwaarde + " (" + kaartRekenWaarde + ")";
	}
}
