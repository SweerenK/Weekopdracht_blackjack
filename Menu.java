package weekopdracht.v3;

import java.util.Scanner;

public class Menu {
	private String[] hoofdmenuOpties = { "Begin met spelen", "Wijzig instellingen", "Uitleg", "Stoppen" };
	private String[] uitlegOpties = { "Hoe werkt Blackjack?", "Hoeveel zijn de kaarten waard?", "Wat zijn de commands?",
			"Terug.." };
	private Scanner scan = new Scanner(System.in);
	private Spel spel = new Spel();
	private Settings setting = new Settings();

	public Spel getSpel() {
		return spel; 
	}
	
	void hoofdmenu() {
		System.out.println("Blackjack!");
		for (int x = 0; x < hoofdmenuOpties.length; x++) {
			System.out.println((x + 1) + ".\t" + hoofdmenuOpties[x]);
		}
		bepaalVervolgHoofdmenu(scan.nextInt());
	}

	void bepaalVervolgHoofdmenu(int hoofdmenuKeuze) {
		switch (hoofdmenuKeuze) {
		case 1:
			spel.resetNewGame();
			spel.setupSpelers();
			spel.alleSpelers.get(0).setNaam(setting.getSpelerNaam());
			spel.beginSpel();
			break;
		case 2:
			System.out.println("Wat is je naam?");
			String naam = scan.next();
			setting.setSpelerNaam(naam);
			setting.setNewStartAmount();
			hoofdmenu();
			break;
		case 3:
			uitlegMenu();
			break;
		case 4:
			spel.sluitProgramma();
			break;
		default:
			hoofdmenu();
		}
	}

	void uitlegMenu() {
		System.out.println("Maak uw keuze (1-" + uitlegOpties.length + "):");
		for (int y = 0; y < uitlegOpties.length; y++) {
			System.out.println((y + 1) + ".\t" + uitlegOpties[y]);
		}
		int uitlegOptie = scan.nextInt();

		switch (uitlegOptie) {
		case 1:
			uitlegSpel();
			endSubMenu();
			break;
		case 2:
			uitlegKaartWaardes();
			endSubMenu();
			break;
		case 3:
			uitlegCommands();
			endSubMenu();
			break;
		default:
			hoofdmenu();
		}
	}

	void endSubMenu() {
		wachtOpEnter();
		uitlegMenu();
	}
	void uitlegSpel() {
		System.out.println(
				"Probeer maximaal 21 punten te krijgen.\nJe verliest als je meer dan 21 punten hebt.\nJe mag passen als je minder dan 21 punten hebt.");
	}

	void uitlegKaartWaardes() {
		System.out.println("KAART\tWAARDE");
		System.out.println("A\t11\n2\t2\n3\t3\n4\t4\n5\t5\n6\t6\n7\t7\n8\t8\n9\t9\n10\t10\nB\t10\nQ\t10\nK\t10");
	}

	void uitlegCommands() {
		System.out.println("ACTIE\tUITLEG");
		System.out.println("k\tNieuwe kaart\np\tPass beurt\ns\tSplit kaarten\nd\tDouble down\nm\tNaar hoofdmenu\nq\tStop programma");
	}

	void wachtOpEnter() {
		System.out.println("\nDoorgaan? (j/n)");
		String voortgang = scan.next();
		if (voortgang.equals("n")) {
			System.out.println("Weet je zeker dat je het programma wilt sluiten? (j/n)");
			voortgang = scan.next();
			if (voortgang.equals("j")) {
				spel.sluitProgramma();
			} else {
				wachtOpEnter();
			}
		}
	}
}
