import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import util.Tuple;

public class DayTen implements Day {

    ArrayList<String> mapOfPipes = new ArrayList<>();
    private final int MAP_SIZE = 140;
    private final Tuple<Integer, Integer> creaturePos = new Tuple<Integer, Integer>(null, null);
    private Tuple<Integer, Integer> currentPos = new Tuple<Integer, Integer>(null, null);;
    int steps = 0;
    char heading;

    @Override
    public void compute() {
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Ten.txt"))) {
            while (sc.hasNextLine()) {
                mapOfPipes.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // finding s
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (mapOfPipes.get(i).charAt(j) == 'S') {
                    creaturePos.setBoth(i, j);
                }
            }
        }
        // System.out.println(creaturePos.toString());
        currentPos.setBoth(creaturePos.getKey() + 1, creaturePos.getValue());
        steps++;
        heading = 'S';

        char pointer = '*';
        System.out.println("Starting traversal.");
        while (pointer != 'S') {
            
            pointer = mapOfPipes.get(currentPos.getKey()).charAt(currentPos.getValue());
            // | - F J L 7
            if (pointer == '|') {
                if (heading == 'N') {
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() - 1)),
                            currentPos.getValue());
                    steps++;
                } else {
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() + 1)),
                            currentPos.getValue());
                    steps++;
                }
            } else if (pointer == '-') {
                if (heading == 'E') {
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() + 1);
                    steps++;
                } else {
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() - 1);
                    steps++;
                }

            } else if (pointer == 'F') {
                if (heading == 'N') {
                    heading = 'E';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() + 1);
                    steps++;
                } else {
                    heading = 'S';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() + 1)),
                            currentPos.getValue());
                    steps++;
                }
            } else if (pointer == 'J') {
                if (heading == 'S') {
                    heading = 'W';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() - 1);
                    steps++;
                } else {
                    heading = 'N';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() - 1)),
                            currentPos.getValue());
                    steps++;
                }
            } else if (pointer == 'L') {
                if (heading == 'S') {
                    heading = 'E';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() + 1);
                    steps++;
                } else {
                    heading = 'N';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() - 1)),
                            currentPos.getValue());
                    steps++;
                }
                // pointer == '7'
            } else {
                if (heading == 'E') {
                    heading = 'S';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey() + 1)),
                            currentPos.getValue());
                    steps++;
                } else {
                    heading = 'W';
                    currentPos.setBoth(mapOfPipes.indexOf(mapOfPipes.get(currentPos.getKey())),
                            currentPos.getValue() - 1);
                    steps++;
                }
            }
        }
        System.out.println(steps / 2);
    }
}
