package converter;

public class Initialization {

    private Class<?> outcome;
    private Object source;

    Initialization(Object source, String outcome) throws ClassNotFoundException {
        this.source = source;
        this.outcome = Class.forName(outcome);
    }


}
