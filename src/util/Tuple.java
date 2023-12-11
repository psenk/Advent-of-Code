package util;

public class Tuple<A, B> implements Pair<A, B> {

    private A key;
    private B value;

    public Tuple(A key, B value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public A getKey() {
        return this.key;
    }

    @Override
    public B getValue() {
        return this.value;
    }

    protected void setKey(A key) {
        this.key = key;
    }

    protected void setValue(B value) {
        this.value = value;
    }

    public String toString() {
        return this.getKey().toString() + " - " + this.getValue().toString();
    }
}
