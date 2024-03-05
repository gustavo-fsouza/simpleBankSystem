package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.PostTransactionBody;
import entities.Transaction;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class TransactionService implements TransactionServiceInterface {

	private Map<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	private Map<Integer, List<Transaction>> accountTransactions = new HashMap<Integer, List<Transaction>>();

	@Override
	public Transaction getTransaction(int transactionId) {
		if (transactions.isEmpty()) {
			
			throw new BusinessException(Errors.NO_TRANSACTION_REGISTERED.getErrorMessage(),
					Errors.NO_TRANSACTION_REGISTERED.getErrorCode());
			
		} else if (!transactions.containsKey(transactionId)) {
			
			throw new BusinessException(Errors.TRANSACTION_NOT_FOUND.getErrorMessage(),
					Errors.TRANSACTION_NOT_FOUND.getErrorCode());
			
		}
		return transactions.get(transactionId);
	}

	@Override
	public int postTransaction(PostTransactionBody body) {
		int transactionId = 1;

		if (!transactions.isEmpty()) {
			transactionId = transactions.size() + 1;
		}

		Transaction newTransaction = new Transaction(transactionId, body.getAccountId(), body.getTransactionDateTime(),
				body.getAmount(), body.getDestinationAcountNumber(), body.getDestinationBranchNumber(),
				body.getDestinationBankNumber(), body.getTransactionType());
		
		transactions.put(transactionId, newTransaction);

		postRelateTransactionToAccount(newTransaction);

		return transactionId;
	}
	
	@Override
	public void postRelateTransactionToAccount(Transaction transaction) {
		List<Transaction> transactionsRecorded = new ArrayList<>();

		try {
			
			transactionsRecorded = getAccountTransactions(transaction.getAccountId());
			transactionsRecorded.add(transaction);
			accountTransactions.put(transaction.getAccountId(), transactionsRecorded);
			
		} catch (BusinessException e) {
			
			if (e.getErrorCode() == Errors.NO_TRANSACTION_REGISTERED.getErrorCode()) {
				transactionsRecorded.add(transaction);
				accountTransactions.put(transaction.getAccountId(), transactionsRecorded);
			}
		}
	}

	@Override
	public void exportTransactionHistory() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Transaction> getAccountTransactions(int accountId) {
		if (accountTransactions.isEmpty()) {
			
			throw new BusinessException(Errors.NO_TRANSACTION_REGISTERED.getErrorMessage(),
					Errors.NO_TRANSACTION_REGISTERED.getErrorCode());
			
		} else if (!accountTransactions.containsKey(accountId)) {
			
			throw new BusinessException(Errors.NO_TRANSACTION_REGISTERED.getErrorMessage(),
					Errors.NO_TRANSACTION_REGISTERED.getErrorCode());
			
		}
		return accountTransactions.get(accountId);
	}
}
