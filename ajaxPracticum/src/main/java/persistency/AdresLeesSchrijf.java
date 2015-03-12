package persistency;

import java.io.IOException;

import bean.Adres;

class AdresLeesSchrijf extends DBTemplate {

	@Override
	protected String getSchrijfStatement(Object adres) throws IOException {
		Adres adresBean = null;
		if (adres instanceof Adres) {
			adresBean = (Adres) adres;
		} else {
			throw new IOException("Het object om weg te schrijven is geen adres");
		}
		return String
				.format("INSERT INTO Adres (Straat, Nr, Postcode, Woonplaats, Land, Actief) VALUES ('%s', '%s', '%s', '%s', '%s', 1)",
						adresBean.getStraat(), adresBean.getNr(), adresBean.getPostcode(), adresBean.getWoonplaats(),
						adresBean.getLand());
	}

	@Override
	protected String getTableName() {
		return "Adres";
	}

	@Override
	protected Adres maakObject(Object[] rij) {
		int id = (int) rij[0];
		String straat = rij[1].toString();
		String nr = rij[2].toString();
		String postcode = rij[3].toString();
		String woonplaats = rij[4].toString();
		String land = rij[5].toString();
		Adres result = new Adres(straat, nr, postcode, woonplaats, land);
		result.setID(id);
		return result;
	}

}
