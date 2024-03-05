package entities;

import entities.enums.AccountType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class PaymentsAccount extends Account {

	public PaymentsAccount(Integer accountId, Integer accountNumber, Integer branchNumber, Double accountBalance,
			Integer partyId, Double accountLimit, AccountType accountType, boolean hasLimit) {
		super(accountId, accountNumber, branchNumber, accountBalance, partyId, accountLimit, accountType, hasLimit);
	}

	@Override
	public void withdraw(double amount) {
		if (getAccountBalance() + getAccountLimit() < amount ) {
			throw new BusinessException(Errors.NOT_ENOUGH_LIMIT.getErrorMessage(),Errors.NOT_ENOUGH_LIMIT.getErrorCode());
		} else {
			accountBalance = getAccountBalance() - amount;
		}
	}

}
