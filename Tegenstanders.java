package weekopdracht.v3;

import java.util.ArrayList;

public class Tegenstanders {

	void AIMoves(Spelers p) {	
		Menu menu = new Menu();
		Spel hetSpel = menu.spel;
		ArrayList<Spelers> deSpelers = hetSpel.alleSpelers;
		
		int minscore = Math.min(deSpelers.get(deSpelers.indexOf(p)).puntenaantal[0],
				deSpelers.get(deSpelers.indexOf(p)).puntenaantal[1]);
		Boolean busted = false;
		p.berekenPuntenaantal();
		if(deSpelers.get(deSpelers.size()-1) != p) {
		while (Math.max(p.puntenaantal[0], p.puntenaantal[1]) < 15) {
			hetSpel.trekKaartInSpel(p, deSpelers.indexOf(p));
			p.berekenPuntenaantal();
			if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).puntenaantal)) {
				String eindwaarde = p.getPuntenaantal(p);
				System.out.println(p.naam + " bust met (" + eindwaarde + ")");
				busted = true;
			}
		}
		}else {
			while (Math.min(p.puntenaantal[0], p.puntenaantal[1]) < 16) {
				hetSpel.trekKaartInSpel(p, deSpelers.indexOf(p));
				p.berekenPuntenaantal();
				if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).puntenaantal)) {
					String eindwaarde = p.getPuntenaantal(p);
					System.out.println(p.naam + " bust met (" + eindwaarde + ")");
					busted = true;
				}
			}
		}
		
		if (!busted) {
			minscore = Math.min(deSpelers.get(deSpelers.indexOf(p)).puntenaantal[0],
					deSpelers.get(deSpelers.indexOf(p)).puntenaantal[1]);
			int maxscore = Math.max(deSpelers.get(deSpelers.indexOf(p)).puntenaantal[0],
					deSpelers.get(deSpelers.indexOf(p)).puntenaantal[1]);
			if (maxscore < 22) {
				System.out.println(p.naam + " past met (" + maxscore + ")");
			} else {
				System.out.println(p.naam + " past met (" + minscore + ")");
			}
		}
		busted = false;
	}
}
