package persistency;

import java.io.IOException;
import java.util.HashMap;

import util.Datum;
import bean.Opdracht;

class OpdrachtLeesSchrijf extends DBTemplate {

	@Override
	protected String getSchrijfStatement(Object opdracht) throws IOException {
		Opdracht opdrachtBean = null;
		if (opdracht instanceof Opdracht) {
			opdrachtBean = (Opdracht) opdracht;
		} else {
			throw new IOException("Het object om weg te schrijven is geen klant");
		}
		return String
				.format("INSERT INTO Opdracht (K_id, KorteOmschrijving, Omschrijving, Geopend, Beheerder) VALUES (%d, '%s', '%s', '%s', '%s')",
						opdrachtBean.getKlantId(), opdrachtBean.getKorteOmschrijving(), opdrachtBean.getOmschrijving(),
						opdrachtBean.getGeopend().getDatumInMySQLFormaat(), opdrachtBean.getBeheerder());
	}

	@Override
	protected String getTableName() {
		return "Opdracht";
	}

	@Override
	protected Opdracht maakObject(Object[] rij) {
		int klantId = (int) rij[1];
		String korteOmschrijving = rij[2].toString();
		String beheerder = rij[6].toString();
		Datum geopend = new Datum(rij[4].toString());
		Opdracht opdracht = new Opdracht(korteOmschrijving, klantId, beheerder, geopend);
		opdracht.setId((int) rij[0]);
		if (rij[3] != null) {
			opdracht.setOmschrijving(rij[3].toString());
		}
		if (rij[5] != null) {
			opdracht.setAfgesloten(new Datum(rij[5].toString()));
		}
		return opdracht;
	}

	@Override
	protected String getZoekQuery(HashMap<String, String> zoekParameters, boolean equals) {
		String query = "";
		query += "SELECT * FROM Opdracht o INNER JOIN Klant k ON o.K_id = k.Id WHERE ";
		String[] keys = zoekParameters.keySet().toArray(new String[zoekParameters.size()]);
		for (int i = 0; i < keys.length; i++) {
			if (equals) {
				query += keys[i] + " LIKE " + String.format("'%s'", zoekParameters.get(keys[i]));
			} else {
				query += keys[i] + " LIKE " + String.format("'%%%s%%'", zoekParameters.get(keys[i]));
			}
			if (i < keys.length - 1) {
				query += " AND ";
			}
		}
		return query;
	}

}
