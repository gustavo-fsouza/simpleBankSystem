package entities.exceptions;

public class BusinessException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
private int errorCode;
	
	public BusinessException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
}
