package weekopdracht.v3;

import java.util.ArrayList;
import java.util.Scanner;

public class Spel {
	static ArrayList<Spelers> alleSpelers = new ArrayList<Spelers>();
	// static Kaartendeck deckkaarten = new Kaartendeck();
	static int aantalSpelers = 1;
	static Scanner scan = new Scanner(System.in);
	static boolean doorspelen = true;
	Kaartendeck spelBegint = new Kaartendeck();

	public static void main(String[] args) {
		// hoofdmenu
		Menu showMenu = new Menu();
		showMenu.hoofdmenu();

		// Kaarten cards = new Kaarten();
		// cards.verdeelKaarten(startSpel);
	}

	void setupSpelers() {
		kiesSpeleraantal();
		maakSpelers();
		alleSpelers.get(0).naam = "Jij";
		if (alleSpelers.size() > 1) {
			alleSpelers.get(alleSpelers.size() - 1).naam = "Dealer";
		}
	}

	static void maakSpelers() {
		for (int x = 0; x < aantalSpelers; x++) {
			Spelers nieuweSpeler = new Spelers();
			nieuweSpeler.naam = "Speler " + (x + 1);
			alleSpelers.add(nieuweSpeler);
		}
	}

	void kiesSpeleraantal() {
		System.out.println("Hoeveel spelers? (Inclusief jezelf)");
		Spel.aantalSpelers = scan.nextInt();
	}

	void beginSpel() {
		spelBegint.maakDeck();
		System.out.println("Geschud:\n" + Kaartendeck.deck + "\n");
		geefEersteKaarten(2);

		while (doorspelen) {

			// startSpel.deelKaartenBijStart();

			doorspelen = false;
			// speel door totdat false

		}
	}

	void geefEersteKaarten(int aantalkaarten) {
		for (int y = 0; y < aantalkaarten; y++) {
			for (Spelers x : alleSpelers) {
				x.gekregenKaarten.add(spelBegint.geefKaart());
				spelBegint.verwijderBovensteKaart();
				if (y == (aantalkaarten - 1)) {
					toonSpelvoortgang(x);
				}
			}
		}
	}

	void toonSpelvoortgang(Spelers a) {
		a.berekenPuntenaantal();
		System.out.println(a + " (" + a.puntenaantal + "):\t" + a.gekregenKaarten);
	}
}
