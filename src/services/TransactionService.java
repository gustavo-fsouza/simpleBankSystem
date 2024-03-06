package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.PostTransactionBody;
import entities.Transaction;
import entities.enums.BankNumbers;
import entities.enums.TransactionType;
import entities.exceptions.BusinessException;
import entities.exceptions.Errors;

public class TransactionService implements TransactionServiceInterface {

	private Map<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	private Map<Integer, List<Transaction>> accountTransactions = new HashMap<Integer, List<Transaction>>();
	
	private AccountService accountService;
	
	private final String writePath = ".//src//exportedTransactions//exportedTransactions.csv";
	
	public TransactionService(AccountService accountService) {
		this.accountService = accountService;
	}
	
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
		if (body.getTransactionType() == TransactionType.INTERNAL_TRANSFER 
				&& (body.getDestinationBankNumber() != BankNumbers.DEFAULT_BANK_NUMBER.getBankNumber())) {
			throw new BusinessException(Errors.FORBIDDEN_DESTINATION_BANK.getErrorMessage(),
					Errors.FORBIDDEN_DESTINATION_BANK.getErrorCode());
		}
		
		int transactionId = 1;

		if (!transactions.isEmpty()) {
			transactionId = transactions.size() + 1;
		}
		
		Transaction newTransaction = new Transaction(transactionId, body.getAccountId(), body.getTransactionDateTime(),
				body.getAmount(), body.getDestinationAcountNumber(), body.getDestinationBranchNumber(),
				body.getTransactionType());
		
		transactions.put(transactionId, newTransaction);

		postRelateTransactionToAccount(newTransaction);
		
		if (body.getTransactionType() == TransactionType.INTERNAL_TRANSFER) {
			accountService.getAccount(body.getAccountId()).withdraw(body.getAmount());;
		}

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
	public void postExportTransactionHistory(int accountId) {
		
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(writePath))){
			List<Transaction> transactionsToExport = getAccountTransactions(accountId);
			
			for (Transaction transaction : transactionsToExport) {
				bw.write(transaction.toString());
				
				bw.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

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
