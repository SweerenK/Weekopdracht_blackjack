package weekopdracht.v3;
import java.util.ArrayList;

public class Spelers {
	ArrayList<Kaarten> gekregenKaarten = new ArrayList<Kaarten>();
	int puntenaantal;
	String naam;
	
	void toonGekregenKaarten(){
		System.out.println(gekregenKaarten);
	}
	
	void berekenPuntenaantal() {
		puntenaantal = 0;
		for(Kaarten kaart : gekregenKaarten) {
			puntenaantal += kaart.kaartRekenWaarde;
		}
	}
	
	@Override
	public String toString() {
		return naam;
	}
}
