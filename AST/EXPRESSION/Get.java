package Stylo.AST.EXPRESSION;

import Stylo.Scanner.Token;

public class Get extends Expr{
	public final Expr object;
	public final Token name;

	public Get(Expr object, Token name){
		this.object = object;
		this.name = name;
	}

	@Override
	public <R> R accept(Visitor<R> visitor){
		return visitor.visitGetExpr(this);
	}
}
