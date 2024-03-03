package entities;

public class Party {
	
	String name;
	Integer partyId;
	
	public Party(String name, Integer partyId) {
		this.name = name;
		this.partyId = partyId;
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
	
	
}
