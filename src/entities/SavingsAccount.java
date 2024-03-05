package entities;

import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class SavingsAccount extends Account {

	public SavingsAccount(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType) {
		super(accountId, accountNumber, branchNumber, accountBalance, partyId, accountLimit, accountType);
	}

	@Override
	public void withdraw(double amount) {
		if (getAccountBalance() <= amount) {
			throw new BusinessException(Errors.INSUFFICIENT_BALANCE.getErrorMessage(),Errors.INSUFFICIENT_BALANCE.getErrorCode());
		} else {
			accountBalance = getAccountBalance() - amount;
		}
	}
}
