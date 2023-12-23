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

    public void setKey(A key) {
        this.key = key;
    }

    public void setValue(B value) {
        this.value = value;
    }

    public void setBoth(A key, B value) {
        setKey(key);
        setValue(value);
    }

    public String toString() {
        return this.getKey().toString() + " - " + this.getValue().toString();
    }

    public boolean compare(Tuple<A, B> otherTuple) {
        if (this.key.equals(otherTuple.getKey()) && this.value.equals(otherTuple.getValue())) {
            return true;
        }
        return false;
    }
}
