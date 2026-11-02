pckage Stylo.Error;

import Stylo.Scanner.Token;

public class ParseError extends RuntimeException{
	public satic ParseError error(Token token, String message){
		Error.error(token, message);
		return new ParseError();
	}
}
