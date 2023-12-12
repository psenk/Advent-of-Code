package util;

public class Pointer {

    private int location;

    public Pointer(int pos) {
        this.location = pos;
    }

    public void setLocation(int pos) {
        this.location = pos;
    }

    public int getLocation() {
        return location;
    }
}
