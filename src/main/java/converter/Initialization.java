package converter;

public class Initialization {

    Class<?>outcome;
    private Object source;

    Initialization(Object source, String outcome) throws ClassNotFoundException {
        this.source = source;
        this.outcome = Class.forName(outcome);
    }



}
