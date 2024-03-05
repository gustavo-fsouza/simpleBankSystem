package entities;

import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public abstract class Account {
	protected Integer accountId;
	protected Integer accountNumber;
	protected Integer branchNumber;
	protected Double accountBalance;
	protected Integer partyId;
	protected Double accountLimit;
	protected AccountType accountType;
	protected boolean hasLimit;
	
	

	public Account(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType, boolean hasLimit) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.branchNumber = branchNumber;
		this.accountBalance = accountBalance;
		this.partyId = partyId;
		this.accountLimit = accountLimit;
		this.accountType = accountType;
		this.hasLimit = hasLimit;
	}
	
	public Account(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.branchNumber = branchNumber;
		this.accountBalance = accountBalance;
		this.partyId = partyId;
		this.accountLimit = accountLimit;
		this.accountType = accountType;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public Integer getBranchNumber() {
		return branchNumber;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public Double getAccountLimit() {
		return accountLimit;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	
	public boolean getHasLimit() {
		return hasLimit;
	}
	
	public void setHasLimit(boolean hasLimit) {
		this.hasLimit = hasLimit;
	}
	
	public void deposit(double amount) {
		accountBalance = getAccountBalance() + amount;
	};
	
	public abstract void withdraw(double amount);
	
	public void changeLimit(double amount) {
		if (hasLimit == false) {
			throw new BusinessException(Errors.NO_LIMIT.getErrorMessage(),Errors.NO_LIMIT.getErrorCode());
		}
		accountLimit = amount;
	}
}
