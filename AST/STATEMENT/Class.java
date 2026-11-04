package Stylo.AST.STATEMENT;

import Stylo.AST.EXPRESSION.Variable;
import Stylo.Scanner.Token;

import java.util.List;

public class Class extends Stmt {
    public final Token name;
    public final Variable superclass;
    public final List<Function> methods;

    public Class(Token name, Variable superclass, List<Function> methods) {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitClassStmt(this);
    }
}
