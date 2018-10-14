package weekopdracht.v3;

import java.util.Scanner;

public class Settings {
	private int aantalSpelers = 0;
	private int[][] defaultChipAmounts = { { 500, 250, 100 }, { 100, 250, 500 } };
	static private int startAmountPlayer = 250, startAmountOpponent = 250;
	private int standaardInzet = 10;
	private String spelerNaam = "Jij";
	private Scanner scan = new Scanner(System.in);
	private boolean doorspelen = true, AIspeelt = false, doubledown = false;

	public boolean isDoubledown() {
		return doubledown;
	}

	public void setDoubledown(boolean doubledown) {
		this.doubledown = doubledown;
	}

	public int getStandaardInzet() {
		return standaardInzet;
	}

	public void setStandaardInzet(int standaardInzet) {
		this.standaardInzet = standaardInzet;
	}

	public boolean isAIspeelt() {
		return AIspeelt;
	}

	public void setAIspeelt(boolean aIspeelt) {
		AIspeelt = aIspeelt;
	}

	public void setDoorspelen(boolean doorspelen) {
		this.doorspelen = doorspelen;
	}

	public boolean getDoorspelen() {
		return doorspelen;
	}

	public int getAantalSpelers() {
		return aantalSpelers;
	}

	public void setAantalSpelers() {
		System.out.println("Hoeveel spelers? (Max. 10 spelers, inclusief jezelf en dealer)");
		int aantalSpelers = scan.nextInt();
		this.aantalSpelers = aantalSpelers;
	}

	public int getStartAmountPlayer() {
		return startAmountPlayer;
	}

	public void setStartAmountPlayer(int chipcount) {
		startAmountPlayer = chipcount;
	}

	public int getStartAmountOpponent() {
		return startAmountOpponent;
	}

	public void setStartAmountOpponent(int chipcount) {
		startAmountOpponent = chipcount;
	}

	public void setNewStartAmount() {
		System.out.println("Met hoeveel chips begin jij?\n1.\t" + defaultChipAmounts[0][0] + " chips (makkelijk)\n2.\t"
				+ defaultChipAmounts[0][1] + " chips (gemiddeld)\n3.\t" + defaultChipAmounts[0][2]
				+ " chips (moeilijk)\n4.\tAnder aantal");
		startAmountPlayer = setSpelerStartAmount(scan.nextInt());
		System.out.println("Met hoeveel chips begint/beginnen jouw tegenstander(s)?\n1.\t" + defaultChipAmounts[1][0]
				+ " chips (makkelijk)\n2.\t" + defaultChipAmounts[1][1] + " chips (gemiddeld)\n3.\t"
				+ defaultChipAmounts[1][2] + " chips (moeilijk)\n4.\tAnder aantal");
		startAmountOpponent = setOpponentStartAmount(scan.nextInt());
	}

	int setSpelerStartAmount(int choice) {
		switch (choice) {
		case 1:
			return 500;
		case 3:
			return 100;
		case 4:
			System.out.println("Kies een nieuw aantal chips om mee te beginnen:");
			int newInt = scan.nextInt();
			return newInt;
		default:
			return 250;
		}
	}

	public int setOpponentStartAmount(int choice) {
		switch (choice) {
		case 1:
			return 100;
		case 3:
			return 500;
		case 4:
			System.out.println("Kies een nieuw aantal chips voor jouw tegenstander(s):");
			int newInt = scan.nextInt();
			return newInt;
		default:
			return 250;
		}
	}

	public String getSpelerNaam() {
		return spelerNaam;
	}

	public void setSpelerNaam(String spelerNaam) {
		this.spelerNaam = spelerNaam;
	}

}
