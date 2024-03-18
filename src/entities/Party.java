package entities;

public class Party {
	
	String name;
	Integer partyId;
	String documentNumber;
	String emailAddress;
	String phoneNumber;
	
	public Party(String name, Integer partyId, String documentNumber, String emailAddress, String phoneNumber) {
		this.name = name;
		this.partyId = partyId;
		this.documentNumber = documentNumber;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPartyId() {
		return partyId;
	}
	
	public String getDocumentNumber() {
		return documentNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
