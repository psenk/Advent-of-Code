package util;

import java.util.HashMap;

public class Node {

    // values
    private char value;
    private int id = 0;
    private Tuple<Integer, Integer> location;
    // node keys A and, distance between A and B
    private HashMap<Tuple<Integer, Integer>, Integer> adjacencyList = new HashMap<>();

    // constructors
    public Node() {
    }

    public Node(int id, char value, int x, int y) {
        this.value = value;
        this.location = new Tuple<>(x, y);
    }

    public Node(char value, int x, int y) {
        this.value = value;
        this.location = new Tuple<>(x, y);
    }

    public Node(int x, int y) {
        this.location = new Tuple<>(x, y);
    }

    // getters and setters
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tuple<Integer, Integer> getLocation() {
        return location;
    }

    public void setLocation(Tuple<Integer, Integer> location) {
        this.location = location;
    }

    public HashMap<Tuple<Integer, Integer>, Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(HashMap<Tuple<Integer, Integer>, Integer> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    // misc methods
    public void addPairToList(Tuple<Integer, Integer> pair) {
        adjacencyList.put(pair, 0);
    }
}