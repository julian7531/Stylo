package Stylo.AST.STATEMENT;

import Stylo.Enviroment.Environment;

public abstract class Stmt {
    public abstract <R> R accept(Visitor<R> visitor);

    public interface Visitor<R> {
        final Environment globals = new Environment();
        R visitBlockStmt(Block stmt);

        R visitClassStmt(Class stmt);

        R visitExpressionStmt(Expression stmt);

        R visitFunctionStmt(Function stmt);

        R visitIfStmt(If stmt);

        R visitPrintStmt(Print stmt);

        R visitReturnStmt(Return stmt);

        R visitVarStmt(Var stmt);

        R visitWhileStmt(While stmt);
    }

}

