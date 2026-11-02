package Stylo.Error;

import Stylo.Scanner.Token;
import Stylo.Scanner.TokenType;

public class Error{
	public statiic boolean hadError = false;
	public static void error(int line, String message){
		report(line, "", message);
	}
		private static void report(int line, String where, String message){
			System.err.println("[line " + line "] Error" + where + ": " + message);
		}
		public static void error(Token token, String message){
			if (token.type == TokenType.EOF){
				report(token.line, " at end", message);
			}
			else {
				report(token.line," at "+ token.lexeme + "'", message);
			}
		}
}
