package bean;

import util.Datum;

public class Opdracht {
	private String korteOmschrijving, omschrijving, beheerder;
	private int id, klantId;
	private Datum geopend, afgesloten;
	private Klant klant;

	public Opdracht(String korteOmschrijving, int klantId, String beheerder, Datum geopend) {
		this.korteOmschrijving = korteOmschrijving;
		this.klantId = klantId;
		this.beheerder = beheerder;
		this.geopend = geopend;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Datum getAfgesloten() {
		return afgesloten;
	}

	public void setAfgesloten(Datum afgesloten) {
		this.afgesloten = afgesloten;
	}

	public String getKorteOmschrijving() {
		return korteOmschrijving;
	}

	public String getBeheerder() {
		return beheerder;
	}

	public int getKlantId() {
		return klantId;
	}

	public Datum getGeopend() {
		return geopend;
	}

	@Override
	public String toString() {
		return String.format("Opdracht: %s", korteOmschrijving);
	}
}
