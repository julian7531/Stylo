package Stylo.Enviroment;

public class ReturnVal extends RuntimeException {
    public final Object value;
    public ReturnVal(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}
