package persistency;

import java.io.IOException;

import bean.Contact;
import bean.ContactType;

class ContactLeesSchrijf extends DBTemplate {

	@Override
	protected String getSchrijfStatement(Object contact) throws IOException {
		Contact con = null;
		if (contact instanceof Contact) {
			con = (Contact) contact;
		} else {
			throw new IOException("Het object om weg te schrijven is geen Contact");
		}
		return String.format("INSERT INTO Contact (K_id, CT_id, contact) VALUES (%d, %d, '%s');", con.getKlantID(), con
				.getContactType().getID(), con.getValue());
	}

	@Override
	protected String getTableName() {
		return "Contact";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Contact maakObject(Object[] rij) {
		ContactType contactType = null;
		switch ((int) rij[2]) {
		case 1:
			contactType = ContactType.Tel;
			break;
		case 2:
			contactType = ContactType.Email;
			break;
		}
		return new Contact(contactType, rij[3].toString());
	}

}
