import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayNine implements Day {

    ArrayList<String> allLines = new ArrayList<>();
    ArrayList<Integer> lineOfDigits;
    ArrayList<ArrayList<Integer>> currentMap = new ArrayList<>();
    long totalSum = 0;
    int count = 0;

    @Override
    public void compute() {
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Nine.txt"))) {
            while (sc.hasNextLine()) {
                allLines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }

        // iterate down rows
        for (int i = 0; i < allLines.size(); i++) {
            lineOfDigits = new ArrayList<>();
            for (String s : allLines.get(i).split(" ")) {
                lineOfDigits.add(Integer.parseInt(s));
            }
            //System.out.println(lineOfDigits.get(j));
            //lineOfDigits = Arrays.asList(Integer.parseInt(allLines.get(i).split(" ");
            scanLine(lineOfDigits);
            currentMap.clear();
        }
    }

    private ArrayList<Integer> clone(ArrayList<Integer> listIn) {
        ArrayList<Integer> clonedList = new ArrayList<>();
        for (Integer i : listIn) {
            clonedList.add(i);
        }
        return clonedList;
    }

    private void scanLine(ArrayList<Integer> lineIn) {
        ArrayList<Integer> currentLineOfIntegers = clone(lineIn);
        ArrayList<Integer> tempArray = new ArrayList<>();

        // creating number triangle
        while (!scanForZeroes(currentLineOfIntegers)) {
            if (currentLineOfIntegers.get(currentLineOfIntegers.size() - 1) != 0) {
                currentLineOfIntegers.add(0);
            }

            currentMap.add(clone(currentLineOfIntegers));

            //System.out.println("Current row of digits: " + currentLineOfIntegers);

            for (int i = 1; i < currentLineOfIntegers.size() - 1; i++) {
                // System.out.println("Adding " + (lineIn.get(i + 1) - lineIn.get(i)) + " to
                // list.");
                tempArray.add(currentLineOfIntegers.get(i) - currentLineOfIntegers.get(i - 1));
            }
            currentLineOfIntegers = clone(tempArray);
            tempArray.clear();
        }

        int pointer = 0;
        for (int i = currentMap.size() - 1; i >= 0; i--) {
            pointer += currentMap.get(i).get(currentMap.get(i).size() - 2);
        }
        System.out.println("Line Number: " + count);
        System.out.println("Next Value: " + pointer);
        totalSum += pointer;        
        
        System.out.println("Total Sum: " + totalSum);
        System.out.println();
    }

    private boolean scanForZeroes(ArrayList<Integer> lineIn) {
        for (int i = 0; i < lineIn.size(); i++) {
            if (lineIn.get(i) != 0) {
                return false;
            }
        }
        //System.out.println("Zeroed out.");
        count++;
        return true;
    }
}