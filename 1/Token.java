package lox;


// In this file we define what a token is
class Token{
	final TokenType type;
	final String lexeme; // lexemes are the raw substrings of the source code, multiple lexemes can be bundled up to create a token
	final Object literal; // what is literal
	final int line;

	Token(TokenType type, String lexeme, Object literal, int line){

		this.type = type;
		this.lexeme = lexeme; 
		this.literal = literal;
		this.line = line;

	}

	public String toString(){
		return type + " " + lexeme + " " + literal;
	}
} 

