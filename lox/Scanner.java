package lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lox.TokenType.*;

class Scanner{
	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	
	private int start = 0; // points to the first lexeme being scanned
    	private int current = 0; // points at the charracter currently being considered
	private int line = 0; // tracks what source line current is on 	
	// the start and current fields are offsets that index into the string. 	
	
	Scanner(String source){ 
		this.source = source;
	}

	List<Token> scanTokens(){
		
		// this loop goes through ______ and scans tokens
		while(!isAtEnd()){
			start = current;
			scanToken();
		}

		tokens.add(new Token(EOF , "" , null, line)); // EOF: End of File, we add an EOF token when all tokens are scanned
		return tokens;

	}


	private boolean isAtEnd(){
		return current >= source.length();
	}

	private void scanToken(){
		char c = advance();
		switch(c){
			case '(': addToken(LEFT_PAREN); break;
			case ')': addToken(RIGHT_PAREN); break;
			case '{': addToken(LEFT_BRACE); break;
			case '}': addToken(RIGHT_BRACE); break;
			case ',': addToken(COMMA); break;
			case '.': addToken(DOT); break;
			case '-': addToken(MINUS); break;
			case '+': addToken(PLUS); break;
			case ';': addToken(SEMI_COLON); break;
			case '*': addToken(STAR); break;

			// the following statements just differentiate between tokens that have 1-2 characters
			case '!': addToken(match('=') ? BANG_EQUAL : BANG); break;
			case '=': addToken(match('=') ? EQUAL_EQUAL : EQUAL); break; 
			case '<': addToken(match('=') ? LESS_EQUAL : LESS); break;
			case '>': addToken(match('=') ? GREATER_EQUAL : GREATER); break;
			

			// the divide operator : "/" needs special handling as comments begin with a slash too
			case '/': 
				  if (match('/')){
					  while (peek() != '\n' && !isAtEnd()){
						  advance();
					  }
				  }else{
					  addToken(SLASH);
				  }
				  break;

			// skip over meaningless characters: new lines and whitespace
			case ' ':
			case '\r':
			case '\t':
				  // ignore whitespace
			case '\n':
				line++; break;

			case '"': string(); break;

			default: 
				  if (isDigit(c)){
					  number();
				  }else if (isAlpha(c)){
					  identifier();
				  }
				  else {
					  Lox.error(line, "unexpected character.");
				  }	

		}// end of the moerse switch statement btw 
	}

	private void identifier(){
		while (isAlphaNumeric(peek())){
			advance();
		}

		String text = source.substring(start, current);
		TokenType type = keywords.get(text);
		if (type == null) type = IDENTIFIER;
			
		addToken(type);
	}

	private boolean isDigit(char c){
		return c >= '0' && c <= '9';
	}
	private void number(){
		while (isDigit(peek())){
			advance();
		}

		if (peek() == '.' && isDigit(peekNext())){
			advance();
			while (isDigit(peek())){
				advance();
			}
		}

		addToken(NUMBER, Double.parseDouble(source.substring(start, current)));

	}
	
	private void string(){
		while (peek() != '"' && !isAtEnd()){
			if (peek() == '\n') line++;
			advance();
		}
		if (isAtEnd()){
			Lox.error(line, "unterminated string");
			return;

		}

			advance();
			String value = source.substring(start + 1 , current -1);
			addToken(STRING, value);
	}


	private boolean match(char expected){
		if (isAtEnd()){
			return false;
		}
		if (source.charAt(current)!= expected){
			return false;
		}
		current ++;
		return true;
	}
	
	// peek() looks at the current unconsumed character
	private char peek(){
		if (isAtEnd()){
			return '\0';
		}
		return source.charAt(current);
	}

	private char peekNext(){
		if (current +1 >= source.length()){
			return '\0';
		}
		return source.charAt(current + 1);
	}


	//isAlpha() checks whether the 
	private boolean isAlpha(char c){
	return (c>= 'a' && c <= 'z') || 
		(c >= 'A' && c <= 'Z') ||
		c == '_';
	}

	private boolean isAlphaNumeric(char c){
		return isAlpha(c) || isDigit(c);
	}

	private static final Map <String, TokenType> keywords;
	static {
		keywords = new HashMap<>();
		keywords.put("and" , AND);
		keywords.put("class" , CLASS);
		keywords.put("else" , ELSE);
		keywords.put("false" , FALSE);
		keywords.put("for" , FOR);
		keywords.put("fun" , FUN);
		keywords.put("if" , IF);
		keywords.put("nil" , NIL);
		keywords.put("or" , OR);
		keywords.put("print" ,PRINT );
		keywords.put("return" , RETURN);
		keywords.put("super" , SUPER);
		keywords.put("this" , THIS);
		keywords.put("true" , TRUE);
		keywords.put("var" , VAR);
		keywords.put("while" , WHILE);
	}
}



	// the advance() method consumes the next character in the source file and returns it
	// advance() is for input , addToken() is for output
	private char advance(){
		current++;
		return source.charAt(current-1);
	}


	// i dont know why there are 2 addTokens(), obviously different methods as they take in different arguments
	private void addToken(TokenType type){
		addToken(type, null);
	}
	private void addToken(TokenType type, Object literal){
		String text = source.substring(start, current);
		tokens.add(new Token(type, text, literal, line));
	}



	
}

