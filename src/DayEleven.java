import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import util.Graph;
import util.Node;
import util.Tuple;

public class DayEleven implements Day {

    int total = 0;

    // char[][] galaxyMap;
    Graph galaxyMap = new Graph();
    char[] emptyCols;
    HashMap<Integer, Node> listOfGalaxies = new HashMap<>();
    // ArrayList<Tuple<Integer, Integer>> listOfPairs = new ArrayList<>();

    public ArrayList<Node> clone(ArrayList<Node> listToClone) {
        ArrayList<Node> newList = new ArrayList<>();
        for (Node n : listToClone) {
            newList.add(n);
        }
        return newList;
    }

    @Override
    public void compute() {

        // creating galaxyMap
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Eleven.txt"))) {
            ArrayList<Node> rowOfSpace = new ArrayList<>();
            int x = 0;
            while (sc.hasNextLine()) {
                int y = 0;
                String line = sc.nextLine();
                for (char c : line.toCharArray()) {
                    rowOfSpace.add(new Node(c, x, y));
                    y++;
                }
                galaxyMap.addToGraph(clone(rowOfSpace));
                galaxyMap.setWidth(rowOfSpace.size());
                x++;
                rowOfSpace.clear();
            }

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND EXCEPTION");
        }

        emptyCols = new char[galaxyMap.get(0).size()];

        expandMap();
        System.out.println("Map expanded.");
        chartGalaxies(galaxyMap);
        System.out.println("Galaxies charted.");
        System.out.println();
        //printMap(galaxyMap);
        System.out.println();
        findPaths();

        for (int i = 0; i < listOfGalaxies.size() - 1; i++) {
            for (Tuple<Integer, Integer> pt : listOfGalaxies.get(i).getAdjacencyList().keySet()) {
                if (pt.getValue() > i) {
                    System.out.print("Pair " + (pt.getKey() + 1) + " - " + (pt.getValue() + 1) + ": ");
                    System.out.println(listOfGalaxies.get(i).getAdjacencyList().get(pt));
                    total += listOfGalaxies.get(i).getAdjacencyList().get(pt);
                }
            }
            System.out.println(total);
        }

        System.out.println("Done.");
    }

    // LOOOOOL WOW
    private void findPaths() {
        Node galaxyOne;
        Node galaxyTwo;
        int steps;
        for (Integer key : listOfGalaxies.keySet()) {
            Node currentGalaxy = listOfGalaxies.get(key);
            for (Tuple<Integer, Integer> path : currentGalaxy.getAdjacencyList().keySet()) {
                galaxyOne = listOfGalaxies.get(path.getKey());
                // System.out.println(galaxyOne.getLocation());
                galaxyTwo = listOfGalaxies.get(path.getValue());
                // System.out.println(galaxyTwo.getLocation());
                steps = 0;
                Tuple<Integer, Integer> pointer = new Tuple<>(galaxyOne.getLocation().getKey(),
                        galaxyOne.getLocation().getValue());

                while (!pointer.compare(galaxyTwo.getLocation())) {
                    // System.out.println("Pointer at: " + pointer);
                    if (pointer.getKey() > galaxyTwo.getLocation().getKey()) {
                        steps++;
                        pointer.setKey(pointer.getKey() - 1);
                    } else if (pointer.getKey() < galaxyTwo.getLocation().getKey()) {
                        steps++;
                        pointer.setKey(pointer.getKey() + 1);
                    } else {
                        if (pointer.getValue() < galaxyTwo.getLocation().getValue()) {
                            steps++;
                            pointer.setValue(pointer.getValue() + 1);
                        } else if (pointer.getValue() > galaxyTwo.getLocation().getValue()) {
                            steps++;
                            pointer.setValue(pointer.getValue() - 1);
                        }

                    }

                }
                // double value = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
                /*
                 * System.out.println("Distance between Galaxy " + galaxyOne.getId() +
                 * " and Galaxy "
                 * + galaxyTwo.getId() + ": " + steps);
                 */
                currentGalaxy.getAdjacencyList().replace(path, steps);
            }
            System.out.println("Galaxy list " + (currentGalaxy.getId() + 1) + " done.");
        }

    }

    private void chartGalaxies(Graph galaxyMapIn) {
        int id = 0;
        for (int i = 0; i < galaxyMapIn.getLength(); i++) {
            for (int j = 0; j < galaxyMapIn.getWidth(); j++) {
                if (galaxyMapIn.get(i).get(j).getValue() == '#') {
                    galaxyMap.get(i).get(j).setLocation(new Tuple<Integer, Integer>(i, j));
                    galaxyMap.get(i).get(j).setId(id + 1);
                    listOfGalaxies.put(id, galaxyMap.get(i).get(j));
                    id++;
                }
            }
        }
        for (int i = 0; i < listOfGalaxies.size(); i++) {
            for (int j = 0; j < listOfGalaxies.size(); j++) {
                if (i != j) {
                    listOfGalaxies.get(i).addPairToList(new Tuple<Integer, Integer>(i, j));
                }
            }
        }
    }

    private void printMap(Graph galaxyMapIn) {
        for (int i = 0; i < galaxyMapIn.getLength(); i++) {
            for (int j = 0; j < galaxyMapIn.getWidth(); j++) {
                if (galaxyMapIn.get(i).get(j).getValue() == '#') {
                    System.out.print(galaxyMapIn.get(i).get(j).getId());
                } else {
                    System.out.print(galaxyMapIn.get(i).get(j).getValue());
                }
            }
            System.out.println();
        }
    }

    private void expandMap() {
        int length = galaxyMap.getLength();
        System.out.println("Rows before: " + length);
        int width = galaxyMap.getWidth();
        System.out.println("Columns before: " + width);
        System.out.println();

        for (int i = 0; i < length; i++) {
            ArrayList<Node> currentLine = galaxyMap.get(i);
            if (!Graph.contains(currentLine, '#')) {
                // if row is clear (has no stars) double it
                // System.out.println("Row " + i + " is clear.");
                // get copy of row
                // System.out.println("Expanding map up and down.");
                // printMap(galaxyMap);
                // System.out.println("--------------------");
                galaxyMap.addToGraph(i, clone(currentLine));
                // printMap(galaxyMap);
                length++;
                i++;
            } else {
                // scanning for empty columns
                for (int j = 0; j < currentLine.size(); j++) {
                    if (currentLine.get(j).getValue() == '#') {
                        emptyCols[j] = 'X';
                    }
                }
            }
        }

        int ct = 0;
        for (int i = 0; i < emptyCols.length; i++) {
            // System.out.print("Column " + i + ": " + emptyCols[i]);
            if (emptyCols[i] == '\u0000') {

                // System.out.println("Expanding map left and right.");
                // printMap(galaxyMapIn);
                // System.out.println("-----------------------------");
                for (int j = 0; j < galaxyMap.getLength(); j++) {
                    galaxyMap.get(j).add(i + ct, new Node('.', 0, 0));
                }
                // printMap(galaxyMap);
                galaxyMap.setWidth(galaxyMap.getWidth() + 1);
                // printMap(galaxyMap);
                ct++;
            }
        }

        length = galaxyMap.getLength();
        System.out.println("Rows after: " + length);
        width = galaxyMap.getWidth();
        System.out.println("Columns after: " + width);
        System.out.println();
    }

}
