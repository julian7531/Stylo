package stylo;

import java.util.List;

import static stylo.TokenType.*;


class Parser{
	private final List<Token> tokens;
	private int current = 0;

	Parser(List<Token> tokens){
		this.tokens = tokens;

	}
	private Expr expression(){
		return equality();
	}

	private Expr equality(){
		Expr expr = comparison();

		while (match(BANG_EQUAL, EQUAL_EQUAL)){
			Token operator = previoius();
			Expr right = comparison();
			expr = new Expr.Binary(expr, operator, right);
		}
		return expr;
	}

	private Expr comparison(){
		Expr expr = term();

		while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)){
			Token operator = previous();
			Expr right = term();
			expr = new Expr.Binary(expr, operator, right);
		}
		return expr;
	}
	
	private Expr term(){
		Expr expr = factor();

		while (match(MINUS, PLUS)){
			Token operator = previous();
			Expr right = factor();
			expr = new Expr.Binary(expr, operator, right);
		}
		return expr;
	}

//a	continue heRE TMR, 
//take it day by day julian you got this, i love you 

	private boolean match(TokenType types){
		for (TokenType type : types){
			if (check(type)){
				advance();
				return true;
			}
		}
		return false;

	}


	//i am but a cog

	private boolean check(TokenType type){
		if (isAtEnd()){
			return false;
		}
		return peek().type == type;

	}

	private Token advance(){
		if (!isAtEnd()){
			current++;
		}
		return previous();
	}

	private boolean isAtEnd(){
		return peek().type == EOF; // u mos alr know what EOF means jyyyyyyy
	}

	private Token peek(){
		return tokens.get(current);
	}

	private Token previous(){
		return tokens.get(current - 1);
	}

}
	
