package weekopdracht.v3;

import java.util.ArrayList;

public class Tegenstanders {
private boolean busted = false;
	
	void AIDealerMoves(Spel hetSpel, Spelers p, ArrayList<Spelers> deSpelers) {
		while ((Math.min(p.getPuntenaantal()[0], p.getPuntenaantal()[1]) < 16) && (Math.max(p.getPuntenaantal()[0], p.getPuntenaantal()[1]) < 16)) {
			hetSpel.trekKaartInSpel(p);
			p.berekenPuntenaantal();
			if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal())) {
				p.berekenPuntenaantal();
				String eindwaarde = p.getPuntenaantal(p);
				System.out.println(p.getNaam() + " bust met (" + eindwaarde + ")");
				busted = true;
			}
		}
		
		
	}
	
	void AISpelerMoves(Spel hetSpel, Spelers p, ArrayList<Spelers> deSpelers) {
		while (Math.max(p.getPuntenaantal()[0], p.getPuntenaantal()[1]) < 15) {
			hetSpel.trekKaartInSpel(p);
			p.berekenPuntenaantal();
			
			if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal())) {
				String eindwaarde = p.getPuntenaantal(p);
				System.out.println(p.getNaam() + " bust met (" + eindwaarde + ")");
				busted = true;
			}
		}
	}
	
	void AICheckWinners() {
		
	}
	
	void AIMoves(Spelers p) {
		Menu menu = new Menu();
		Spel hetSpel = menu.getSpel();
		ArrayList<Spelers> deSpelers = hetSpel.alleSpelers;

		int minscore = Math.min(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[0],
				deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[1]);
		busted = false;
		
		p.berekenPuntenaantal();
		if (deSpelers.get(deSpelers.size() - 1) != p) {
			AISpelerMoves(hetSpel, p, deSpelers);
		} else {
			AIDealerMoves(hetSpel, p, deSpelers);
		}
		

		if (!busted) {
			minscore = Math.min(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[0],
					deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[1]);
			int maxscore = Math.max(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[0],
					deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[1]);
			if (maxscore < 22) {
				System.out.println(p.getNaam() + " past met (" + maxscore + ")");
			} else {
				System.out.println(p.getNaam() + " past met (" + minscore + ")");
			}
		}
		busted = false;
	}
}
