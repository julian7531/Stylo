package Stylo.AST.EXPRESSION;

import Stylo.Scanner.Token;

public class Super extends Expr{
	public final Token keyword;
	public final Token mehtod;

	public Super(Token keyword, Token method){
		this.keyword = keyword;
		this.method = method;
	}

	@Override
	public <R> R accept(Visitor<R> visitor){
		return visitor.visitSuperExpr(this);
	}
}
