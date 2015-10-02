package productParts;

public class ExceptionHandler extends Exception {
	
	String message;
	
	public ExceptionHandler(String errMessage){
		message = errMessage;
	}

	public String getMessage() {
		return message;
	}

}
