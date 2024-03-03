package services;

import entities.Party;

public interface PartyServiceInterface {
	public Party getParty(Integer partyId);
	public int postParty(String name);
}
