package services;

import DTO.PostAccountBody;
import entities.Account;

public interface AccountServiceInterface {
	public int postAccount(PostAccountBody body);  
	public Account getAccount(int accountId);
}
