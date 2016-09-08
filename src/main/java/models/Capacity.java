package models;

public class Capacity {

    private final String center;
    private final int value;

    public String getCenter() {
        return center;
    }

    public int getValue() {
        return value;
    }

    public Capacity(String center, int value) {
        this.center = center;
        this.value = value;
    }
}
