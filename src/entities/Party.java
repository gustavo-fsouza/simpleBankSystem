package entities;

public class Party {
	
	String name;
	Integer partyId;
	String documentNumber;
	
	public Party(String name, Integer partyId, String documentNumber) {
		this.name = name;
		this.partyId = partyId;
		this.documentNumber = documentNumber;
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

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	
	public String getDocumentNumber() {
		return documentNumber;
	}
}
