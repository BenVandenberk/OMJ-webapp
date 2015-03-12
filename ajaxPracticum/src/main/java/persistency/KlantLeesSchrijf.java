package persistency;

import java.io.IOException;

import bean.Klant;

class KlantLeesSchrijf extends DBTemplate {

	@Override
	protected String getSchrijfStatement(Object klant) throws IOException {
		Klant klantBean = null;
		if (klant instanceof Klant) {
			klantBean = (Klant) klant;
		} else {
			throw new IOException("Het object om weg te schrijven is geen klant");
		}
		return String
				.format("INSERT INTO Klant (Naam, BTW, FacturatieAdr, AfleverAdr) VALUES ('%s', '%s', %d, %d)", klantBean
						.getNaam(), klantBean.getBtwNummer(), klantBean.getFacturatieAdres().getID(), klantBean.getAfleverAdres()
						.getID());
	}

	@Override
	protected String getTableName() {
		return "Klant";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Klant maakObject(Object[] rij) {
		Klant klant = new Klant(rij[1].toString());
		klant.setId((int) rij[0]);
		if (rij[2] != null) {
			klant.setBtwNummer(rij[2].toString());
		}
		if (rij[3] != null) {
			klant.setFacturatieAdresId((int) rij[3]);
		}
		if (rij[4] != null) {
			klant.setAfleverAdresId((int) rij[4]);
		}
		return klant;
	}

}
