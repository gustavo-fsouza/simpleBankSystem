package services;

import java.util.HashMap;
import java.util.Map;

import DTO.PostAccountBody;
import entities.Account;
import entities.PaymentsAccount;
import entities.SavingsAccount;
import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class AccountService implements AccountServiceInterface {
	
	private Map<Integer, Account> accounts = new HashMap<>();

	@Override
	public int postAccount(PostAccountBody body) {
		int accountId = 1;
		
		if (!accounts.isEmpty()) {
			accountId = accounts.size() + 1;
		}
		
		Account newAccount = accountsConstructorChoicer(body, accountId);
		accounts.put(accountId, newAccount);
		return accountId;
	}

	@Override
	public Account getAccount(int accountId) {
		if (accounts.isEmpty()) {
			throw new BusinessException(Errors.NO_ACCOUNT_REGISTERED.getErrorMessage(),Errors.NO_ACCOUNT_REGISTERED.getErrorCode());
		}
		else if (!accounts.containsKey(accountId)) {
			throw new BusinessException(Errors.ACCOUNT_NOT_FOUND.getErrorMessage(),Errors.ACCOUNT_NOT_FOUND.getErrorCode());
		}
		return accounts.get(accountId);
	}
	
	public Account accountsConstructorChoicer(PostAccountBody body, Integer accountId){
		Account newAccount;
		if(body.getAccountType() == AccountType.PAYMENTS) {
			newAccount = createPaymentsAccount(body, accountId);
		}
		else {
			newAccount = createSavingsAccount(body, accountId);
		}
		return newAccount;
	}
	
	public PaymentsAccount createPaymentsAccount(PostAccountBody body, Integer accountId) {
		return new PaymentsAccount(
				accountId, 
				body.getAccountNumber(), 
				body.getBranchNumber(), 
				body.getAccountBalance(),
				body.getPartyId(), 
				body.getAccountLimit(), 
				body.getAccountType(),
				body.getHasLimit()
				);
	}
	
	public SavingsAccount createSavingsAccount(PostAccountBody body, Integer accountId) {
		return new SavingsAccount(
				accountId, 
				body.getAccountNumber(), 
				body.getBranchNumber(), 
				body.getAccountBalance(),
				body.getPartyId(), 
				body.getAccountLimit(), 
				body.getAccountType()
				);
	}
	
}
