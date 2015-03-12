package persistency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.Adres;
import bean.Contact;
import bean.ContactType;
import bean.Klant;
import bean.Opdracht;

public class DBAccess {

	private static DBAccess uniqueInstance;
	private KlantLeesSchrijf kls;
	private ContactLeesSchrijf cls;
	private AdresLeesSchrijf als;
	private OpdrachtLeesSchrijf ols;

	private DBAccess() {
		kls = new KlantLeesSchrijf();
		cls = new ContactLeesSchrijf();
		als = new AdresLeesSchrijf();
		ols = new OpdrachtLeesSchrijf();
	}

	public static DBAccess getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DBAccess();
		}
		return uniqueInstance;
	}

	public void schrijfOpdracht(Opdracht opdracht) throws IOException, SQLException {
		ols.schrijfObject(opdracht);
	}

	public void schrijfKlant(Klant klant) throws IOException, SQLException {
		als.schrijfObject(klant.getFacturatieAdres());
		int adresID = als.getLastID();
		klant.getFacturatieAdres().setID(adresID);
		klant.getAfleverAdres().setID(adresID);

		kls.schrijfObject(klant);
		int klantID = kls.getLastID();
		klant.getEmail().setKlantID(klantID);
		klant.getTel().setKlantID(klantID);

		cls.schrijfObject(klant.getEmail());
		cls.schrijfObject(klant.getTel());
	}

	public ArrayList<Klant> zoekKlanten(String id, String naam) throws IOException, SQLException {
		HashMap<String, String> zoekParameters = new HashMap<String, String>();
		zoekParameters.put("Id", id);
		zoekParameters.put("Naam", naam);
		ArrayList<Klant> klanten = kls.zoekObjecten(zoekParameters, false);
		vulAdressenIn(klanten);
		vulContactenIn(klanten);
		return klanten;
	}

	public ArrayList<Opdracht> zoekOpdrachten(String klant, String beheerder) throws IOException, SQLException {
		HashMap<String, String> zoekParameters = new HashMap<String, String>();
		zoekParameters.put("Naam", klant);
		zoekParameters.put("Beheerder", beheerder);
		ArrayList<Opdracht> opdrachten = ols.zoekObjecten(zoekParameters, false);
		vulKlantenIn(opdrachten);
		return opdrachten;
	}

	private void vulKlantenIn(List<Opdracht> opdrachten) throws IOException, SQLException {
		for (Opdracht opdracht : opdrachten) {
			if (opdracht.getKlantId() > 0) {
				Klant klant = kls.zoekObject(opdracht.getKlantId());
				opdracht.setKlant(klant);
			}
		}
	}

	private void vulAdressenIn(List<Klant> klanten) throws IOException, SQLException {
		for (Klant klant : klanten) {
			if (klant.getFacturatieAdresId() > 0) {
				Adres facturatieAdres = als.zoekObject(klant.getFacturatieAdresId());
				klant.setFacturatieAdres(facturatieAdres);
			}
			if (klant.getAfleverAdresId() > 0) {
				Adres afleverAdres = als.zoekObject(klant.getAfleverAdresId());
				klant.setAfleverAdres(afleverAdres);
			}
		}
	}

	private void vulContactenIn(List<Klant> klanten) throws IOException, SQLException {
		HashMap<String, String> zoekParameters;
		for (Klant klant : klanten) {
			zoekParameters = new HashMap<String, String>();
			zoekParameters.put("K_id", Integer.toString(klant.getId()));
			ArrayList<Contact> contacten = cls.zoekObjecten(zoekParameters, true);
			for (Contact contact : contacten) {
				if (contact.getContactType() == ContactType.Tel) {
					klant.setTel(contact);
				} else if (contact.getContactType() == ContactType.Email) {
					klant.setEmail(contact);
				}
			}
		}
	}
}
