package weekopdracht.v3;

import java.util.ArrayList;

public class Tegenstanders {

	void AIMoves(Spelers p) {
		Menu menu = new Menu();
		Spel hetSpel = menu.getSpel();
		ArrayList<Spelers> deSpelers = hetSpel.alleSpelers;

		int minscore = Math.min(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[0],
				deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal()[1]);
		Boolean busted = false;
		p.berekenPuntenaantal();
		if (deSpelers.get(deSpelers.size() - 1) != p) {
			while (Math.max(p.getPuntenaantal()[0], p.getPuntenaantal()[1]) < 15) {
				hetSpel.trekKaartInSpel(p, deSpelers.indexOf(p));
				p.berekenPuntenaantal();
				if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal())) {
					String eindwaarde = p.getPuntenaantal(p);
					System.out.println(p.getNaam() + " bust met (" + eindwaarde + ")");
					busted = true;
				}
			}
		} else {
			while (Math.min(p.getPuntenaantal()[0], p.getPuntenaantal()[1]) < 16) {
				hetSpel.trekKaartInSpel(p, deSpelers.indexOf(p));
				p.berekenPuntenaantal();
				if (hetSpel.checkBusted(deSpelers.get(deSpelers.indexOf(p)).getPuntenaantal())) {
					String eindwaarde = p.getPuntenaantal(p);
					System.out.println(p.getNaam() + " bust met (" + eindwaarde + ")");
					busted = true;
				}
			}
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
