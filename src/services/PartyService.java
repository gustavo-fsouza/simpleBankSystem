package services;

import java.util.HashMap;
import java.util.Map;

import entities.Party;

public class PartyService implements PartyServiceInterface {

	private Map<Integer, Party> parties = new HashMap<Integer, Party>();

	@Override
	public Party getParty(Integer partyId) {
		return parties.get(partyId);
	}

	@Override
	public int postParty(String name) {
		int partyId = 1;
		
		if (!parties.isEmpty()) {
			partyId = parties.size() + 1;
		}
		
		Party newParty = new Party(name, partyId);
		parties.put(partyId, newParty);
		return partyId;
	}

}
