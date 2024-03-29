package entities.exceptions;

public enum Errors {
	
	    NO_ACCOUNT_REGISTERED(1, "Nenhuma conta cadastrada no sistema"),
	    ACCOUNT_NOT_FOUND(2, "A conta informada nao foi encontrada"),
	    INSUFFICIENT_BALANCE(3, "Saldo insuficiente!"),
		NOT_ENOUGH_LIMIT(4, "Limite insuficiente para saque!"),
		NO_LIMIT(5, "A conta nao possui limite"),
		NO_TRANSACTION_REGISTERED(6, "Nenhuma transacao registrada"),
		TRANSACTION_NOT_FOUND(7, "Transacao nao encontrada"),
		FORBIDDEN_DESTINATION_BANK(8, "No momento apenas sao permitidas transferencias internas entre contas"),
		NO_PARTY_REGISTERED(9,"Nenhuma pessoa registrada"),
		TRANSFER_TIME_LIMIT(10,"Valor nao permitido para esse horario"),
		ACCOUNT_ALREADY_EXISTS(11,"Ja existe conta com esse numero"),
		INVALID_DOCUMENT(12, "Documento invalido"),
		PARTY_ALREADY_EXISTS(13,"Usuario ja cadastrado");
	
	
	
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
