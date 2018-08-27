package converter.classTree;

public class Pair {

    private String attrubuteName;

    private Object value;

    private Class type;

    public Pair() {
    }

    public Pair(Class type) {
        this.type = type;
    }

    public Pair(String attrubuteName) {
        this.attrubuteName = attrubuteName;
    }

    public Pair(String attrubuteName, String value) {
        this.attrubuteName = attrubuteName;
        this.value = value;
    }

    public Pair(String attrubuteName, Class type) {
        this.attrubuteName = attrubuteName;
        this.type = type;
    }

    public String getAttrubuteName() {
        return attrubuteName;
    }

    public void setAttrubuteName(String attrubuteName) {
        this.attrubuteName = attrubuteName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

}
