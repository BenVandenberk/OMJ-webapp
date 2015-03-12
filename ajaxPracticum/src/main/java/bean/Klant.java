package bean;

public class Klant {

	private String naam, btwNummer;
	private Adres facturatieAdres, afleverAdres;
	private Contact tel, email;
	private int facturatieAdresId, afleverAdresId, id;

	public Klant(String naam, Adres facturatieAdres, Adres afleverAdres, Contact tel, Contact email) {
		this.naam = naam;
		this.facturatieAdres = facturatieAdres;
		this.afleverAdres = afleverAdres;
		this.tel = tel;
		this.email = email;
		this.btwNummer = "";
	}

	public Klant(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public String getBtwNummer() {
		return btwNummer;
	}

	public void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}

	public Adres getFacturatieAdres() {
		return facturatieAdres;
	}

	public void setFacturatieAdres(Adres facturatieAdres) {
		this.facturatieAdres = facturatieAdres;
	}

	public Adres getAfleverAdres() {
		return afleverAdres;
	}

	public void setAfleverAdres(Adres afleverAdres) {
		this.afleverAdres = afleverAdres;
	}

	public int getFacturatieAdresId() {
		return facturatieAdresId;
	}

	public void setFacturatieAdresId(int facturatieAdresId) {
		this.facturatieAdresId = facturatieAdresId;
	}

	public int getAfleverAdresId() {
		return afleverAdresId;
	}

	public void setAfleverAdresId(int afleverAdresId) {
		this.afleverAdresId = afleverAdresId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contact getTel() {
		return tel;
	}

	public void setTel(Contact tel) {
		this.tel = tel;
	}

	public Contact getEmail() {
		return email;
	}

	public void setEmail(Contact email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Klant: %s\nBTW-nummer: %s\nFacturatie adres: %s\nAflever adres: %s\n%s\n%s", naam, btwNummer,
				facturatieAdres, afleverAdres, tel, email);
	}

}
