package bean;

public class Adres {

	private String straat, nr, postcode, woonplaats, land;
	private int ID;

	public Adres(String straat, String nr, String postcode, String woonplaats, String land) {
		this.straat = straat;
		this.nr = nr;
		this.postcode = postcode;
		this.woonplaats = woonplaats;
		this.land = land;
	}

	public String getStraat() {
		return straat;
	}

	public String getNr() {
		return nr;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public String getLand() {
		return land;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", straat, nr, postcode, woonplaats, land);
	}

}
