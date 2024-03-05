package services;

import java.util.List;

import DTO.PostTransactionBody;
import entities.Transaction;

public interface TransactionServiceInterface {
	public Transaction getTransaction(int transactionId);
	public int postTransaction(PostTransactionBody body);
	public List<Transaction> getAccountTransactions(int accountId);
	public void postRelateTransactionToAccount(Transaction transaction);
	public void exportTransactionHistory();
}
