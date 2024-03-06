package entities.enums;

public enum BankNumbers {
	DEFAULT_BANK_NUMBER(120);
	
	private int bankNumber;
	
	BankNumbers(int bankNumber){
		this.bankNumber = bankNumber;
	}
	
	public int getBankNumber() {
		return bankNumber;
	}
}
