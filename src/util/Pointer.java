package util;

public class Pointer<T> {

    private T location;

    public Pointer(T pos) {
        this.location = pos;
    }

    public void setLocation(T pos) {
        this.location = pos;
    }

    public T getLocation() {
        return location;
    }
}
