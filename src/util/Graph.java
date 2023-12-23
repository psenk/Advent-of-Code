package util;

import java.util.ArrayList;

public class Graph {

    // values
    private int length;
    private int width;
    private ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    // constructors
    public Graph() {
        generateGraph(0, 0);
    }

    public Graph(int len, int wid) {
        this.length = len;
        this.width = wid;
        generateGraph(len, wid);
    }

    // getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // misc methods
    public void generateGraph(int length, int width) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                graph.get(i).add(new Node(i, j));
            }
        }
    }

    public void addToGraph(ArrayList<Node> row) {
        graph.add(row);
        length++;
    }

    public void addToGraph(int index, ArrayList<Node> row) {
        graph.add(index, row);
        length++;
    }

    public void addNodeToGraph(int x, int y, Node node) {
        graph.get(x).add(y, node);
    }

    public ArrayList<Node> get(int rowNum) {
        return graph.get(rowNum);
    }

    public Node getNode(int row, int node) {
        return graph.get(row).get(node);
    }

    public static boolean contains(ArrayList<Node> row, char item) {
        for (Node n : row) {
            if (n.getValue() == item) {
                return true;
            }
        }
        return false;
    }
}
