package weekopdracht.v3;

public class Kaarten {
	private String kaartsoort, kaartwaarde;
	private int kaartRekenWaarde;

	public String getKaartsoort() {
		return kaartsoort;
	}

	public void setKaartsoort(String kaartsoort) {
		this.kaartsoort = kaartsoort;
	}

	public String getKaartwaarde() {
		return kaartwaarde;
	}

	public void setKaartwaarde(String kaartwaarde) {
		this.kaartwaarde = kaartwaarde;
	}

	public int getKaartRekenWaarde() {
		return kaartRekenWaarde;
	}

	public void setKaartRekenWaarde(int kaartRekenWaarde) {
		this.kaartRekenWaarde = kaartRekenWaarde;
	}

	@Override
	public String toString() {
		if (kaartRekenWaarde == 11) {
			return kaartsoort + " " + kaartwaarde + " (" + (kaartRekenWaarde - 10) + "/" + kaartRekenWaarde + ")";
		} else {
			return kaartsoort + " " + kaartwaarde + " (" + kaartRekenWaarde + ")";
		}
	}
}
