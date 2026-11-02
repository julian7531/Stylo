package Lox.error;

import Lox.Scanner.Token;

public class RuntimeError extends RuntimeException{
	public final Token token ;
	public static boolean hadRuntimeError = false;

	public RuntimeError(Token token String message){
		super(message);
		this.token = token
	}
	public static void runtimeError(RuntimeError error){
		System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
		hadRuntimeError = true;
	}
	
}
	
