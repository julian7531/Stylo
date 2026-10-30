package Stylo.Utils;

import Stylo.Error.RuntimeError;
import Stylo.Scanner.Token;

public class Utils {
    public static String stringify(Object object) {
        if (object == null) return "nil";
        if (object instanceof Double) {
            String text = object.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }
        return object.toString();
    }

    public static  boolean isTruthy(Object object) {
        if (object == null) return false;
        if (object instanceof Boolean) return (boolean) object;
        if (object instanceof String) return !((String)object).isEmpty();
        if (object instanceof Number){
            Number nObject = ((Number) object);
            if (nObject.longValue() == 0 && nObject.doubleValue() != 0){
                return true;
            }
            return nObject.longValue() != 0;
        }
        return true;
    }

    public static  boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }

    public static  void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new RuntimeError(operator, "Operand must be a number.");
    }

    public static void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;

        throw new RuntimeError(operator, "Operands must be numbers.");
    }

}

