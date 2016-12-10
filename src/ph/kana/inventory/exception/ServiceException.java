package ph.kana.inventory.exception;

public class ServiceException extends Exception {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
}
