package Stylo.Enviroment;

import Stylo.AST.STATEMENT.Function;
import Stylo.Interpreter.Interpreter;

import java.util.List;

public class StyloFunction implements StyloCallable {

    private final Function declaration;
    private final Environment closure;
    private final boolean isInitializer;
    public StyloFunction(Function declaration, Environment closure,
                boolean isInitializer) {
        this.isInitializer = isInitializer;
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));
        }
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (ReturnVal returnValue) {
            if (isInitializer) return closure.getAt(0, "this");
            return returnValue.value;
        }
        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }
    public StyloFunction bind(StyloInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new StyloFunction(declaration, environment,
                isInitializer);
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }

}
