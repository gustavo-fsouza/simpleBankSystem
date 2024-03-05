package entities;

import java.time.LocalDateTime;

import entities.enums.TransactionType;

public class Transaction {
	
	private Integer transactionId;
	private Integer accountId;
	private LocalDateTime transactionDateTime;
	private Double amount;
	private Integer destinationAcountNumber;
	private Integer destinationBranchNumber;
	private Integer destinationBankNumber;
	private TransactionType transactionType;
	
	public Transaction(Integer transactionId, Integer accountId, LocalDateTime transactionDateTime, Double amount,
			Integer destinationAcountNumber, Integer destinationBranchNumber, Integer destinationBankNumber,
			TransactionType transactionType) {
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionDateTime = transactionDateTime;
		this.amount = amount;
		this.destinationAcountNumber = destinationAcountNumber;
		this.destinationBranchNumber = destinationBranchNumber;
		this.destinationBankNumber = destinationBankNumber;
		this.transactionType = transactionType;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public Double getAmount() {
		return amount;
	}

	public Integer getDestinationAcountNumber() {
		return destinationAcountNumber;
	}

	public Integer getDestinationBranchNumber() {
		return destinationBranchNumber;
	}

	public Integer getDestinationBankNumber() {
		return destinationBankNumber;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}
	
	
	
}
