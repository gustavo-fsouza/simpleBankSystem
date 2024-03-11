package services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.PostAccountBody;
import entities.Account;
import entities.Party;
import entities.PaymentsAccount;
import entities.SavingsAccount;
import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class AccountService implements AccountServiceInterface {
	
	private Map<Integer, Account> accounts = new HashMap<>();
	private Map<String, Account> accountsByNumber = new HashMap<>();
	private Map<Integer, List<Party>> accountParties = new HashMap<Integer, List<Party>>();
	private final int DEFAULT_BRANCH_NUMBER = 1;
	private double TRANSFER_AMOUNT_LIMIT = 1000.00;
	private LocalTime TRANSFER_TIMELIMIT = LocalTime.parse("22:00:00");
	
	private PartyService partyService;
	
	public AccountService(PartyService partyService) {
		this.partyService = partyService;
	}
	
	@Override
	public int postAccount(PostAccountBody body) {
		int accountId = 1;
		
		if (!accounts.isEmpty()) {
			accountId = accounts.size() + 1;
		}
		
		Account newAccount = accountsConstructorChoicer(body, accountId);
		accounts.put(accountId, newAccount);
		
		postRelatePartyToAccount(newAccount);
		postRelateAccoundIdAndNumber(newAccount);
		
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
	
	public int getDefaultBranchNumber() {
		return DEFAULT_BRANCH_NUMBER;
	}
	
	public double getTransferAmountLimit() {
		return TRANSFER_AMOUNT_LIMIT;
	}
	
	public LocalTime getTransferTimeLimit() {
		return TRANSFER_TIMELIMIT;
	}

	@Override
	public void postRelatePartyToAccount(Account account) {
		List<Party> parties = new ArrayList<>();

		try {
			
			parties = getAccountParties(account.getAccountId());
			
			parties.add(partyService.getParty(account.getPartyId()));
			
			accountParties.put(account.getAccountId(), parties);
			
		} catch (BusinessException e) {
			
			if (e.getErrorCode() == Errors.NO_PARTY_REGISTERED.getErrorCode()) {
				parties.add(partyService.getParty(account.getPartyId()));
				accountParties.put(account.getPartyId(), parties);
			}
		}
		
	}

	@Override
	public List<Party> getAccountParties(int accountId) {
		if (accountParties.isEmpty()) {
			
			throw new BusinessException(Errors.NO_PARTY_REGISTERED.getErrorMessage(),
					Errors.NO_PARTY_REGISTERED.getErrorCode());
			
		} else if (!accountParties.containsKey(accountId)) {
			
			throw new BusinessException(Errors.NO_PARTY_REGISTERED.getErrorMessage(),
					Errors.NO_PARTY_REGISTERED.getErrorCode());
			
		}
		return accountParties.get(accountId);
	}

	@Override
	public Account getAccountByAccountAndBranchNumber(String accountAndBranchNumber) {
		if (accountsByNumber.isEmpty()) {
			
			throw new BusinessException(Errors.NO_ACCOUNT_REGISTERED.getErrorMessage(),
					Errors.NO_ACCOUNT_REGISTERED.getErrorCode());
			
		} else if (!accountsByNumber.containsKey(accountAndBranchNumber)) {
			
			throw new BusinessException(Errors.ACCOUNT_NOT_FOUND.getErrorMessage(),
					Errors.ACCOUNT_NOT_FOUND.getErrorCode());
			
		}
		return accountsByNumber.get(accountAndBranchNumber);
	}
	
	@Override
	public void postRelateAccoundIdAndNumber(Account account) {
		List<Account> accounts = new ArrayList<>();

		String accountAndBranchNumber = account.getAccountNumber().toString() + account.getBranchNumber().toString();
		
		try {	
			
			getAccountByAccountAndBranchNumber(accountAndBranchNumber);
			
			accounts.add(account);
			
			
			accountsByNumber.put(accountAndBranchNumber, account);
			
		} catch (BusinessException e) {
			
			if(accountExists(account)) {
				accounts.add(account);

				accountsByNumber.put(accountAndBranchNumber, account);
			}
			
			
		}
	}
	
	public boolean accountExists(Account account) {
		return getAccount(account.getAccountId()) instanceof Account;
	}
}
