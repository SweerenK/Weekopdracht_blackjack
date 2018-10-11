package weekopdracht.v3;

import java.util.Scanner;

public class Menu {
	String titel = "Blackjack!";
	String[] hoofdmenuOpties = {"Begin met spelen", "Uitleg", "Stoppen"};
	String[] uitlegOpties = {"Hoe werkt Blackjack?","Hoeveel zijn de kaarten waard?","Wat zijn de commands?", "Terug.."};
	Scanner scan = new Scanner(System.in);
	Spel spel = new Spel();
	
	void hoofdmenu() {
		System.out.println("Blackjack!");
		for(int x = 0; x < hoofdmenuOpties.length; x++) {
			System.out.println((x+1) + ".\t" + hoofdmenuOpties[x]);
		}
		
		bepaalVervolgactie(scan.nextInt());
		// eigen method
		
	}
	
	void bepaalVervolgactie(int hoofdmenuKeuze) {
		if(hoofdmenuKeuze == 1) {
			spel.setupSpelers();
			spel.beginSpel();
		}else if(hoofdmenuKeuze == 2){
			uitlegMenu();
		}else {
			spel.sluitProgramma();
		}
	}
	
	void uitlegMenu() {
		System.out.println("Maak uw keuze (1-" +uitlegOpties.length + "):");
		for(int y = 0; y < uitlegOpties.length; y++) {
			System.out.println((y+1) + ".\t" + uitlegOpties[y]);
		}
		int uitlegOptie = scan.nextInt();
		
		switch(uitlegOptie) {
		case 1:
			uitlegSpel();
			wachtOpEnter();
			uitlegMenu();
			break;
		case 2:
			uitlegKaartWaardes();
			wachtOpEnter();
			uitlegMenu();
			break;
		case 3:
			uitlegCommands();
			wachtOpEnter();
			uitlegMenu();
			break;
		default:
			hoofdmenu();
		}
	}
		
	void uitlegSpel() {
		System.out.println("Probeer maximaal 21 punten te krijgen.\nJe verliest als je meer dan 21 punten hebt.\nJe mag passen als je minder dan 21 punten hebt.");
	}
	void uitlegKaartWaardes() {
		System.out.println("KAART\tWAARDE");
		System.out.println("A\t11\n2\t2\n3\t3\n4\t4\n5\t5\n6\t6\n7\t7\n8\t8\n9\t9\n10\t10\nB\t10\nQ\t10\nK\t10");
	}
	
	void uitlegCommands() {
		System.out.println("ACTIE\tUITLEG");
		System.out.println("k\tNieuwe kaart\np\tPass beurt\ns\tSplit kaarten\nd\tDouble down\nq\tStop spel");
	}
	
	void wachtOpEnter() {
		System.out.println("\nDoorgaan? (j/n)");
		String voortgang = scan.next();
		if(voortgang.equals("n"))
			spel.sluitProgramma();
			
	}
}
