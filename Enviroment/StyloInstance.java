package Stylo.Enviroment;

import Stylo.Error.RuntimeError;
import Stylo.Scanner.Token;

import java.util.HashMap;
import java.util.Map;

public class StyloInstance {

    private StyloClass klass;
    private final Map<String, Object> fields = new HashMap<>();
    public StyloInstance(StyloClass klass) {
        this.klass = klass;
    }

    public Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        StyloFunction method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }
    public void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
