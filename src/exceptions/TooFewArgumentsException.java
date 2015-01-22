package exceptions;

public class TooFewArgumentsException extends WrongArgumentException{
	public String getMessage(){
		return "error: must pass two command line arguments";
	}

}
