package exceptions;

public class ArgumentLengthsDifferException extends WrongArgumentException{
	public String getMessage(){
		return "error: length of command line arguments differs";
	}
}
