package DTO;

public class PostPartyBody {
	private String name;
	private String documentNumber;
	
	public PostPartyBody(String name, String documentNumber) {
		this.name = name;
		this.documentNumber = documentNumber;
	}

	public String getName() {
		return name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}
	
	
}
