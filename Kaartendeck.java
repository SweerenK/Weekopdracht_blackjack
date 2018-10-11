package weekopdracht.v3;

import java.util.ArrayList;
import java.util.Collections;

public class Kaartendeck {
	String[] kaartsoort = { "harten", "klaveren", "ruiten", "schoppen" };
	String[] kaartwaarde = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "Q", "K" };
	static ArrayList<Kaarten> deck = new ArrayList<Kaarten>();

	// Methoden Kaartendeck

	void maakDeck() {
		vulDeck();
		schudDeck();
	}

	void vulDeck() {
		for (int x = 0; x < kaartsoort.length; x++) {
			for (int y = 0; y < kaartwaarde.length; y++) {
				Kaarten kaart = new Kaarten();
				kaart.kaartsoort = kaartsoort[x];
				kaart.kaartwaarde = kaartwaarde[y];
				kaart.kaartRekenWaarde = bepaalKaartRekenWaarde(y);
				deck.add(kaart);
			}
		}
	}

	void schudDeck() {
		Collections.shuffle(deck);
	}

	void toonDeck() {
		System.out.println("Het deck is geschud: \n" + deck);
	}

	int bepaalKaartRekenWaarde(int indexWaarde) {
		int waarde;
		switch (indexWaarde % 13) {
		case 0:
			waarde = 11;
			break;
		case 10:
			waarde = 10;
			break;
		case 11:
			waarde = 10;
			break;
		case 12:
			waarde = 10;
			break;
		default:
			waarde = (indexWaarde % 13) + 1;
		}
		return waarde;
	}

	// Methoden Kaarten geven
	/*
	void deelKaartenBijStart() {
		for (int y = 0; y < Spel.aantalSpelers; y++) {
			Spel.alleSpelers.get(y).gekregenKaarten.add(deck.get(0));
			System.out.println("Speler " + Spel.alleSpelers.get(y) + " heeft gekregen: "
					+ Spel.alleSpelers.get(y).gekregenKaarten);
			deck.remove(0);
		}
	} */

	Kaarten geefKaart() {
		return deck.get(0);
	}

	void verwijderBovensteKaart() {
		deck.remove(0);
	}
}