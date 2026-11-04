package Stylo.Enviroment;

import Stylo.Interpreter.Interpreter;

import java.util.List;

public interface StyloCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}