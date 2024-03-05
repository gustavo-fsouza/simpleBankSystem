package entities.exceptions;

public enum Errors {
	
	    NO_ACCOUNT_REGISTERED(1, "Nenhuma conta cadastrada no sistema"),
	    ACCOUNT_NOT_FOUND(2, "A conta informada não foi encontrada"),
	    INSUFFICIENT_BALANCE(3, "Saldo insuficiente!"),
		NOT_ENOUGH_LIMIT(4, "Limite insuficiente para saque!"),
		NO_LIMIT(5, "A conta não possui limite"),
		NO_TRANSACTION_REGISTERED(6, "Nenhuma transação registrada"),
		TRANSACTION_NOT_FOUND(7, "Transação não encontrada");
	
	
	    private String errorMessage;
	    private int errorCode;
	 
	    Errors(int errorCode, String errorMessage) {
	    	this.errorCode = errorCode;
	        this.errorMessage = errorMessage;
	    }
	 
	    public String getErrorMessage() {
	        return errorMessage;
	    }
	    
	    public int getErrorCode() {
	        return errorCode;
	    }
	     
}
