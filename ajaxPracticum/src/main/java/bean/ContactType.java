package bean;

public enum ContactType {
	Tel, Email;

	public int getID() {
		switch (this) {
		case Tel:
			return 1;
		case Email:
			return 2;
		default:
			return -1;
		}
	}
}
