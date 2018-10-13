package weekopdracht.v3;

public class Kaarten {
	String kaartsoort, kaartwaarde;
	int kaartRekenWaarde;
	
	@Override
	public String toString() {
		if(kaartRekenWaarde == 11) {
			return kaartsoort + " " + kaartwaarde + " (" + (kaartRekenWaarde-10) + "/" + kaartRekenWaarde + ")";
		}else {
		return kaartsoort + " " + kaartwaarde + " (" + kaartRekenWaarde + ")";
		}
	}
}
