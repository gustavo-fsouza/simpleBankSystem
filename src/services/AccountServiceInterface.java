package services;

import java.util.List;

import DTO.PostAccountBody;
import entities.Account;
import entities.Party;

public interface AccountServiceInterface {
	public int postAccount(PostAccountBody body);  
	public Account getAccount(int accountId);
	public void postRelatePartyToAccount(Account account);
	public List<Party> getAccountParties(int accountId);
}
