package entities;

import entities.enums.AccountType;

public class PaymentsAccount extends Account {

	public PaymentsAccount(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType) {
		super(accountId, accountNumber, branchNumber, accountBalance, partyId, accountLimit, accountType);
	}

	@Override
	public void withdraw(double amount) {
		if (getAccountBalance() + getAccountLimit() < amount ) {
			System.out.println("Limite infusifiencte para saque!");
		} else {
			accountBalance = getAccountBalance() - amount;
		}
	}

}
