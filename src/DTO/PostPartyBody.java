package DTO;

public class PostPartyBody {
	private String name;
	private String documentNumber;
	private String emailAddress;
	private String phoneNumber;
	
	public PostPartyBody(String name, String documentNumber, String emailAddress, String phoneNumber) {
		this.name = name;
		this.documentNumber = documentNumber;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}
