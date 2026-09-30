package lox;

//TokenType.java is an enum for the different types of tokens, whether single, or multiple characters, to literals and keywords

//wtf does enum even mean?
enum TokenType{
	//single character tokens
	//
	LEFT_PAREN, RIGHT_PAREN, 
	LEFT_BRACE, RIGHT_BRACE, 
	COMMA, DOT, 
	MINUS, PLUS, 
	SEMI_COLON, SLASH, STAR,

	// 1-2 character tokens
	//
	BANG, BANG_EQUAL, 
	EQUAL, EQUAL_EQUAL, 
	GREATER, GREATER_EQUAL, 
	LESS, LESS_EQUAL,

	//literals
	IDENTIFIER, STRING, NUMBER,
	
	//key words
	AND, CLASS, ELSE, 
	FALSE, FUN, FOR, IF, NIL, 
	OR, PRINT, RETURN, SUPER, 
	THIS, TRUE, VAR, WHILE, 

	EOF // EOF : End Of File 

}

