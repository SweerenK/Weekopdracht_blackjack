package weekopdracht.v3;

import java.util.ArrayList;
import java.util.Scanner;

public class Spel {
	static ArrayList<Spelers> alleSpelers = new ArrayList<Spelers>();
	private Spelers player;
	private Kaartendeck spelBegint = new Kaartendeck();
	private Settings setting = new Settings();
	private Tegenstanders tegenstander = new Tegenstanders();
	static Menu showMenu = new Menu();
	private Scanner scan = new Scanner(System.in);
	private int aantalGames = 0;

	public static void main(String[] args) {
		showMenu.hoofdmenu();
	}

	void setupSpelers() {
		setting.setAantalSpelers();
		maakSpelers();
		player = alleSpelers.get(0);
		player.setNaam(setting.getSpelerNaam());

		if (alleSpelers.size() > 1) {
			alleSpelers.get(alleSpelers.size() - 1).setNaam("Dealer");
		}
	}

	void maakSpelers() {
		for (int x = 0; x < setting.getAantalSpelers(); x++) {
			Spelers nieuweSpeler = new Spelers();
			nieuweSpeler.setNaam("Gast " + (x + 1));
			if (x == 0) {
				nieuweSpeler.setChipcount(setting.getStartAmountPlayer());
			} else {
				nieuweSpeler.setChipcount(setting.getStartAmountOpponent());
			}
			alleSpelers.add(nieuweSpeler);
		}
	}

	void beginSpel() {
		System.out.println("===== Start Game " + ++aantalGames + " =====\n");
		resetNewGame();
		spelBegint.deck.clear();
		spelBegint.vulDeck();
		spelBegint.schudDeck();
		spelBegint.toonDeck();
		geefEersteKaarten(2);
		spelVoortgang();
	}

	
	void spelVoortgang() {
		checkBlackjack();
		userActionToContinue();
		System.out.println("===== Einde Game " + aantalGames + " =====\n");

		if (player.getChipcount() > 0) {
			beginSpel(); // !
		} else {
			System.out.println("Je hebt onvoldoende chips om door te spelen.\nHet spel is afgelopen.");
			setting.setDoorspelen(false);
		}
	}

	void checkBlackjack() {
		while (setting.getDoorspelen()) {
			if (checkBlackjack(player)) {
				System.out.println("\nGefeliciteerd, je hebt Blackjack!");
				beurtVoorbij();
			} else {
				commandChecker(actieCommand());
			}
		}
	}
	
	void userActionToContinue() {
		System.out.println("\tTyp 'v' voor vervolg..");
		while (!scan.next().equals("v")) {
			if (scan.next().equals("q")) {
				sluitProgramma();
			}
			System.out.println("\tTyp 'v' voor vervolg..");
		}
	}
	
	public String actieCommand() {
		System.out
				.println("\nJe hebt " + player.getPuntenaantal(player) + ". Wat wil je doen?\n(Typ 'help' voor info)");
		String command = scan.next();
		return command;
	}

	void geefEersteKaarten(int aantalkaarten) {
		for (int y = 0; y < aantalkaarten; y++) {
			for (Spelers x : alleSpelers) {
				x.gekregenKaarten.add(spelBegint.geefKaart());
				spelBegint.verwijderBovensteKaart();
				if (y == (aantalkaarten - 1)) {
					x.berekenPuntenaantal();
					toonSpelvoortgang(x);
				}
			}
		}
	}
	
	void commandChecker(String tekst) {
		switch (tekst) {
		case "help":
			showMenu.uitlegCommands();
			break;
		case "q":
			sluitProgramma();
			break;
		case "k":
			trekKaartInSpel(player);
			player.berekenPuntenaantal();
			if (checkBusted(player.getPuntenaantal())) {
				setting.setDoorspelen(false);
				System.out.println(
						"Helaas! Je hebt (" + Math.min(player.getPuntenaantal()[0], player.getPuntenaantal()[1]) + ")");
				beurtVoorbij();
			}
			break;
		case "p":
			setting.setDoorspelen(false);
			player.berekenPuntenaantal();
			String eindwaarde = player.getPuntenaantal(player);
			System.out.println(setting.getSpelerNaam() + " past met (" + eindwaarde + "): \t" + player.gekregenKaarten);
			beurtVoorbij();
			break;
		case "d":
			System.out.println(
					"Double down! Deze game wordt de inzet verdubbeld naar " + 2 * setting.getStandaardInzet() + ".");
			System.out.print("Jouw laatste kaart: ");
			setting.setDoubledown(true);
			trekKaartInSpel(player);
			player.berekenPuntenaantal();
			setting.setDoorspelen(false);
			if (checkBusted(player.getPuntenaantal())) {
				System.out.println(
						"Helaas! Je hebt (" + Math.min(player.getPuntenaantal()[0], player.getPuntenaantal()[1]) + ")");
			}
			beurtVoorbij();

			break;
		case "s":
			System.out.println("Split is nog niet geimplementeerd. Maak een andere keuze.");
			// split: twee handen
			// beurtVoorbij()
			break;
		case "m":
			System.out.println("Het spel wordt afgebroken en je keert terug naar het menu.\n");
			resetNewTourney();
			showMenu.hoofdmenu();
		default:
			// geen actie gedefinieerd
		}
	}

	void trekKaartInSpel(Spelers z) {
		ArrayList<Kaarten> spelerArray = z.gekregenKaarten;
		spelerArray.add(spelBegint.geefKaart());
		spelBegint.verwijderBovensteKaart();
		System.out.println(z.getNaam() + " pakt " + spelerArray.get(spelerArray.size() - 1));
		z.berekenPuntenaantal();
	}
	
	void resetNewTourney() {
		alleSpelers.clear();
		setting.setSpelerNaam("Jij");
		setting.setDoorspelen(true);
		setting.setAIspeelt(false);
		setting.setDoubledown(false);
		setting.setStartAmountPlayer(250);
		setting.setStartAmountOpponent(250);
	}
	void resetNewGame() {
		for (Spelers x : alleSpelers) {
			x.setPuntenaantal(new int[] { 0, 0 });
			x.gekregenKaarten.clear();
		}
		setting.setDoorspelen(true);
		setting.setDoubledown(false);
	}

	int[] defineTwoHandValues(int[] speleraantal, int[] dealeraantal) {
		int dealerpunten, spelerpunten;
		if (Math.max(speleraantal[0], speleraantal[1]) < 22) {
			spelerpunten = Math.max(speleraantal[0], speleraantal[1]);
		} else {
			spelerpunten = Math.min(speleraantal[0], speleraantal[1]);
		}

		if (Math.max(dealeraantal[0], dealeraantal[1]) < 22) {
			dealerpunten = Math.max(dealeraantal[0], dealeraantal[1]);
		} else {
			dealerpunten = Math.min(dealeraantal[0], dealeraantal[1]);
		}
		//System.out.println(spelerpunten + "S<>D" + dealerpunten);
		return new int[]{spelerpunten, dealerpunten};
	}
	
	void compareTwoHandValues(Spelers speler, int spelerpunten, int dealerpunten) {
		if ((spelerpunten == dealerpunten) && (spelerpunten < 22)) {
			if (alleSpelers.indexOf(speler) == 0) {
				System.out.print("\n\tPush! Je krijgt je inzet terug.\n");
			}

		} else if (((spelerpunten > dealerpunten) && (spelerpunten < 22))
				|| ((dealerpunten > 21 && spelerpunten < 22))) {
			if (alleSpelers.indexOf(speler) == 0) {
				System.out.print("\n\tGefeliciteerd, jij wint!\n");
				if (setting.isDoubledown()) {
					speler.setChipcount(speler.getChipcount() + setting.getStandaardInzet());
				}
			}
			if (checkBlackjack(speler)) {
				System.out.println("\t" + speler.getNaam() + " krijgt anderhalf keer de inzet terug wegens blackjack.");
				speler.setChipcount(speler.getChipcount() + (setting.getStandaardInzet()/2));
			}
			speler.setChipcount(speler.getChipcount() + setting.getStandaardInzet());
		} else {
			if (alleSpelers.indexOf(speler) == 0) {
				System.out.print("\n\tJe verliest je inzet.\n");
				if (setting.isDoubledown()) {
					speler.setChipcount(speler.getChipcount() - setting.getStandaardInzet());
				}
			}
			speler.setChipcount(speler.getChipcount() - setting.getStandaardInzet());
		}
	}
	
	void checkIfWinner(Spelers speler) {
		speler.berekenPuntenaantal();
		alleSpelers.get(alleSpelers.size()-1).berekenPuntenaantal();
		int[] speleraantal = speler.getPuntenaantal();
		int[] dealeraantal = alleSpelers.get(alleSpelers.size() - 1).getPuntenaantal();
		int[] punten;

		punten = defineTwoHandValues(speleraantal, dealeraantal);
		int spelerpunten = punten[0];
		int dealerpunten = punten[1];
		compareTwoHandValues(speler, spelerpunten, dealerpunten);
		
	}

	void beurtVoorbij() {
		if (alleSpelers.size() > 1) {
			System.out.println("\nTegenstander(s) aan zet!\n\tTyp 'v' voor vervolg..");
			while (!scan.next().equals("v")) {
				System.out.println("Tegenstander(s) aan zet!\n\tTyp 'v' voor vervolg..");
			}
			for (int p = 1; p < alleSpelers.size(); p++) {
				tegenstander.AIMoves(alleSpelers.get(p));
			}
			for(Spelers t : alleSpelers) {
				checkIfWinner(t);
			}
		}
		setting.setDoorspelen(false);
	}

	boolean checkBlackjack(Spelers a) {
		if (Math.max(a.getPuntenaantal()[0], a.getPuntenaantal()[1]) == 21 && a.getGekregenKaarten().size() == 2) {
			a.getPuntenaantal()[0] = 21;
			a.getPuntenaantal()[1] = 21;
			return true;
		} else {
			return false;
		}
	}

	void toonSpelvoortgang(Spelers a) {
		a.berekenPuntenaantal();
		if (checkBlackjack(a)) {
			if (alleSpelers.get(alleSpelers.size() - 1) != a) {
				System.out.println(a.getChipcount() + " chips\t" + a + "\t\t" + "(21)\t" + a.getGekregenKaarten() + "\tBLACKJACK!");
			} else {
				System.out.println("\t\t" + a + "\t\t" + "(21)\t" + a.getGekregenKaarten() + "\tBLACKJACK!");
			}
		} else if (alleSpelers.indexOf(a) != alleSpelers.size() - 1) {
			System.out.println(
					a.getChipcount() + " chips\t" + a + "\t\t" + "(" + a.getPuntenaantal(a) + ")\t" + a.getGekregenKaarten());
		} else {
			System.out.println("\t\t" + a + "\t\t" + "(" + a.getPuntenaantal(a) + ")\t" + a.getGekregenKaarten());
		}
	}

	boolean checkBusted(int[] waarde) {
		if (waarde[0] > 21 && waarde[1] > 21) {
			return true;
		} else {
			return false;
		}
	}

	void sluitProgramma() {
		System.out.println("Het programma wordt gesloten.");
		scan.close();
		System.exit(0);
	}
}
