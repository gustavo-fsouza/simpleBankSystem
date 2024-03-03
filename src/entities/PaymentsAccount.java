package entities;

import entities.enums.AccountType;

public class PaymentsAccount extends Account {

	public PaymentsAccount(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType) {
		super(accountId, accountNumber, branchNumber, accountBalance, partyId, accountLimit, accountType);
	}

	

}
