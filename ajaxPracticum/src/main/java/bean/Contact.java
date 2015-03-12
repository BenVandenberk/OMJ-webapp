package bean;

public class Contact {

	private ContactType contactType;
	private String value;
	private int klantID;

	public Contact(ContactType contactType, String value) {
		this.contactType = contactType;
		this.value = value;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public String getValue() {
		return value;
	}

	public void setKlantID(int id) {
		klantID = id;
	}

	public int getKlantID() {
		return klantID;
	}

	@Override
	public String toString() {
		return value;
	}
}
