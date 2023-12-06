import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;

public class DayThree implements Day {

    HashSet<Character> symbolsSet = new HashSet<>(Arrays.asList('@', '&', '=', '*', '/', '+', '-', '%', '$', '#'));
    final int ARRAY_LENGTH = 140;
    char[][] engineSchematic = new char[ARRAY_LENGTH][ARRAY_LENGTH];
    int sumOfPartNumbers = 0;

    public void printSchematic() {
        for (int i = 0; i < engineSchematic.length; i++) {
            for (int j = 0; j < engineSchematic[0].length; j++) {
                System.out.print(engineSchematic[i][j]);
            }
            System.out.println();
        }
    }

    public int createEdgeList(int row, int col) {
        int edgeList = 0b11111111;

        //N, NE, E, SE, S, SW, W, NW
        //0, 1,  2, 3,  4, 5,  6, 7

        if (row == 0) { // N, NE, NW
            edgeList = edgeList&0b00111110;
        }
        if (row == ARRAY_LENGTH - 1) { // S, SE, SW
            edgeList = edgeList&0b11100011;
        }
        if (col == 0) { // NW, W, SW
            edgeList = edgeList&0b11111000;
        }
        if (col == ARRAY_LENGTH - 1) { // NE, E, SE
            edgeList = edgeList&0b10001111;
        }
        return edgeList;
    }

/*
 * [-1][-1] | [-1][0] | [-1][1]
 * ----------------------------
 * [0][-1] | [0][0] | [0][1]
 * ----------------------------
 * [1][-1] | [1][0] | [1][1]
 */

    public boolean scanForSymbol(int row, int col) {
        final int EDGE_LENGTH = 8;
        int edgeList = createEdgeList(row, col);
        for (int i = 0; i < EDGE_LENGTH; i++) {
            // N 
            if ((edgeList&0b10000000)>>7 == 1) {
                if (symbolsSet.contains(engineSchematic[row - 1][col])) {
                    //System.out.print(engineSchematic[row - 1][col]);
                    return true;
                }
            }
            // NE
            if ((edgeList&0b01000000)>>6 == 1) {
                if (symbolsSet.contains(engineSchematic[row - 1][col + 1])) {
                    //System.out.print(engineSchematic[row - 1][col + 1]);
                    return true;
                }
            }
            // E
            if ((edgeList&0b00100000)>>5 == 1) {
                if (symbolsSet.contains(engineSchematic[row][col + 1])) {
                    //System.out.print(engineSchematic[row][col + 1]);
                    return true;
                }
            }
            // SE
            if ((edgeList&0b00010000)>>4 == 1) {
                if (symbolsSet.contains(engineSchematic[row + 1][col + 1])) {
                    //System.out.print(engineSchematic[row + 1][col + 1]);
                    return true;
                }
            }
            // S
            if ((edgeList&0b00001000)>>3 == 1) {
                if (symbolsSet.contains(engineSchematic[row + 1][col])) {
                    //System.out.print(engineSchematic[row + 1][col]);
                    return true;
                }
            }
            // SW
            if ((edgeList&0b00000100)>>2 == 1) {
                if (symbolsSet.contains(engineSchematic[row + 1][col - 1])) {
                    //System.out.print(engineSchematic[row + 1][col - 1]);
                    return true;
                }
            }
            // W
            if ((edgeList&0b00000010)>>1 == 1) {
                if (symbolsSet.contains(engineSchematic[row][col - 1])) {
                    //System.out.print(engineSchematic[row][col - 1]);
                    return true;
                }
            }
            // NW
            if ((edgeList&0b00000001) == 1) {
                if (symbolsSet.contains(engineSchematic[row - 1][col - 1])) {
                    //System.out.print(engineSchematic[row - 1][col - 1]);
                    return true;
                }
            }
        }
        return false;
    }

    public void compute() {
        try (Scanner sc = new Scanner(new File(".\\Day Three.txt"))) {
            // adding file to 2d char array
            int rowCount = 0;
            while (sc.hasNextLine()) {
                engineSchematic[rowCount] = sc.nextLine().toCharArray();
                rowCount++;
            }

            // searching array for digits, testing for adjacency
            // scanForSymbol(3, 0); // for testing
            for (int i = 0; i < engineSchematic.length; i++) {
                for (int j = 0; j < engineSchematic[0].length; j++) {
                    if (Character.isDigit(engineSchematic[i][j])) {
                        if (scanForSymbol(i, j)) {
                            String partNumber = "";
                            //found a number next to a symbol
                            //recreate part number
                            int pt1 = j;
                            int pt2 = j;
                            while (true) {
                                if ((pt1 - 1) >= 0 && Character.isDigit(engineSchematic[i][pt1 - 1])) {
                                    pt1--;
                                    continue;
                                }
                                break;
                            }
                            while (true) {
                                if ((pt2 + 1) < ARRAY_LENGTH && Character.isDigit(engineSchematic[i][pt2 + 1])) {
                                    pt2++;
                                    continue;
                                }
                                break;

                            }
                            for (int k = pt1; k <= pt2; k++) {
                                partNumber += engineSchematic[i][k];
                            }
                            j = pt2;
                            sumOfPartNumbers += Integer.valueOf(partNumber);
                        }                      
                    }
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sum of part numbers: " + sumOfPartNumbers);
        //printSchematic();
    }
}

/*
 * 1. scan through array searching for digits
 * 2. once digit found, scan around digit for symbols in set
 * 3a. if symbol found, scan around digit for remainder of number, add to total
 * 3b. if no symbol found, continue scanning
 */

/*
 * if row = 0, don't search up
 * if col = 0, don't search left
 * if row = length, don't search down
 * if col = length, don't search right
 */