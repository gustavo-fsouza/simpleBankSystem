package services;

import DTO.PostPartyBody;
import entities.Party;

public interface PartyServiceInterface {
	public Party getParty(Integer partyId);
	public int postParty(PostPartyBody body);
}
