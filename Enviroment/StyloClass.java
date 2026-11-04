package Stylo.Enviroment;


import Stylo.Interpreter.Interpreter;

import java.util.List;
import java.util.Map;

public class StyloClass implements StyloCallable{
    public final String name;
    private final Map<String, StyloFunction> methods;

    final StyloClass superclass;
    public StyloClass(String name, StyloClass superclass,
             Map<String, StyloFunction> methods) {
        this.superclass = superclass;
        this.name = name;
        this.methods = methods;
    }

    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        StyloInstance instance = new StyloInstance(this);
        StyloFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }
    public StyloFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }
        if (superclass != null) {
            return superclass.findMethod(name);
        }
        return null;
    }
    @Override
    public int arity() {
        StyloFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }

    @Override
    public String toString() {
        return name;
    }
}