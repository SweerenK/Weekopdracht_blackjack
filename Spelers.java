package weekopdracht.v3;

import java.util.ArrayList;

public class Spelers {
	ArrayList<Kaarten> gekregenKaarten = new ArrayList<Kaarten>();
	private int puntenaantal[] = { 0, 0 };
	private int chipcount;
	private String naam;

	public ArrayList<Kaarten> getGekregenKaarten() {
		return gekregenKaarten;
	}

	public void setGekregenKaarten(ArrayList<Kaarten> gekregenKaarten) {
		this.gekregenKaarten = gekregenKaarten;
	}

	public int getChipcount() {
		return chipcount;
	}

	public void setChipcount(int chipcount) {
		this.chipcount = chipcount;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int[] getPuntenaantal() {
		return puntenaantal;
	}

	public void setPuntenaantal(int[] puntenaantal) {
		this.puntenaantal = puntenaantal;
	}

	void toonGekregenKaarten() {
		System.out.println(gekregenKaarten);
	}

	String getPuntenaantal(Spelers t) {
		t.berekenPuntenaantal();
		if (t.puntenaantal[0] == t.puntenaantal[1]) {
			return Integer.toString(t.puntenaantal[0]);
		} else if (Math.max(t.puntenaantal[0], t.puntenaantal[1]) > 21) {
			return Integer.toString(Math.min(t.puntenaantal[0], t.puntenaantal[1]));
		} else {
			return Math.min(t.puntenaantal[0], t.puntenaantal[1]) + "/"
					+ Math.max(t.puntenaantal[0], t.puntenaantal[1]);
		}
	}

	void berekenPuntenaantal() {
		for (int x = 0; x < puntenaantal.length; x++) {
			puntenaantal[x] = 0;
		}

		for (int kaart = 0; kaart < gekregenKaarten.size(); kaart++) {
			if (gekregenKaarten.get(kaart).getKaartRekenWaarde() != 11) {
				puntenaantal[0] += gekregenKaarten.get(kaart).getKaartRekenWaarde();
				puntenaantal[1] += gekregenKaarten.get(kaart).getKaartRekenWaarde();
			}

			else {
				if (puntenaantal[0] < 11) {
					if (puntenaantal[1] >= 11) {
						puntenaantal[0] += 1;
						puntenaantal[1] += 1;
					} else if (puntenaantal[0] == 1 && puntenaantal[1] == 11) {
						puntenaantal[0] += 1;
						puntenaantal[1] += 1;
					} else {
						puntenaantal[0] += 1;
						puntenaantal[1] += 11;
					}
				} else if (puntenaantal[0] > 10) {
					if (puntenaantal[1] > 11) {
						puntenaantal[0] += 1;
						puntenaantal[1] += 1;
					}
				} else {
					puntenaantal[0] += 1;
					puntenaantal[1] += 11;
				}

				if (gekregenKaarten.get(kaart).getKaartRekenWaarde() == 11 && puntenaantal[0] == 11) {
					puntenaantal[0] -= 10;
				}
			}
		}
	}

	@Override
	public String toString() {
		return naam;
	}
}
