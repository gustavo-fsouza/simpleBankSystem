package DTO;

import entities.enums.AccountType;

public class PostAccountBody {
	Integer accountNumber;
	Integer branchNumber;
	Double accountBalance;
	Integer partyId;
	Double accountLimit;
	AccountType accountType;
	
	public PostAccountBody(Integer accountNumber, Integer branchNumber, Double accountBalance, Integer partyId,
			Double accountLimit, AccountType accountType) {
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
	
	
}

