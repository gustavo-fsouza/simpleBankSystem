package entities;

import entities.enums.AccountType;

public class SavingsAccount extends Account {

	public SavingsAccount(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType) {
		super(accountId, accountNumber, branchNumber, accountBalance, partyId, accountLimit, accountType);
	}
	
	
}
