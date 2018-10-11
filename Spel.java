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
			System.out.println("\nJe hebt " + getPuntenaantal() + ". Wat wil je doen?\n(Typ 'help' voor info)");
			String command = scan.next();
			commandChecker(command);
			// hier verder
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
	
	void trekKaartInSpel(Spelers z, int spelersindex) {
		alleSpelers.get(spelersindex).gekregenKaarten.add(spelBegint.geefKaart());
		spelBegint.verwijderBovensteKaart();
		alleSpelers.get(0).berekenPuntenaantal();
		toonSpelvoortgang(alleSpelers.get(spelersindex));
	}

	void commandChecker(String tekst) {
		switch (tekst) {
		case "help":
			Menu helpCommands = new Menu();
			helpCommands.uitlegCommands();
			break;
		case "q":
			sluitProgramma();
			break;
		case "k":
			trekKaartInSpel(alleSpelers.get(0), 0);
			//controleer en door of andere spelers
			break;
		case "p":
			doorspelen = false;
			alleSpelers.get(0).berekenPuntenaantal();
			System.out.println("Speler past met (" + alleSpelers.get(0).puntenaantal + "): \t"+alleSpelers.get(0).gekregenKaarten);
			//andere spelers doen hun zetten
			break;
		case "d":
			// double down
			System.out.println("d");
			// geef 1 kaart en andere spelers doen hun zetten
			break;
		case "s":
			System.out.println("s");
			// split: twee handen
			//andere spelers doen hun zetten
			break;
		default:
			// geen actie gedefinieerd
		}
	}

	int getPuntenaantal() {
		return alleSpelers.get(0).puntenaantal;
	}

	void toonSpelvoortgang(Spelers a) {
		a.berekenPuntenaantal();
		System.out.println(a + " (" + a.puntenaantal + "):\t" + a.gekregenKaarten);
	}
	
	void sluitProgramma() {
		System.out.println("Het programma wordt gesloten.");
		System.exit(0);
	}
}
