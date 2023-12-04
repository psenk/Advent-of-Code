import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        String workingString;
        String keyString = "";
        int pointer = 0;
        char digitOne = Character.MIN_VALUE;
        char digitTwo = Character.MIN_VALUE;
        String lineAnswer;
        int total = 0;

        final HashMap<String, Integer> numDict = new HashMap<>();
        numDict.put("zero", 0);
        numDict.put("one", 1);
        numDict.put("two", 2);
        numDict.put("three", 3);
        numDict.put("four", 4);
        numDict.put("five", 5);
        numDict.put("six", 6);
        numDict.put("seven", 7);
        numDict.put("eight", 8);
        numDict.put("nine", 9);

        try (Scanner sc = new Scanner(new File(".\\Day 1.txt"))) {
            while (sc.hasNextLine()) {
                workingString = sc.nextLine();
                pointer = 0;
                keyString = "";

                // replacing string num with int
                // nonsense substrings are discarded
                for (int i = 0; i < workingString.length(); i++) {
                    if (Character.isDigit(workingString.charAt(i))) {
                        keyString += Character.toString(workingString.charAt(i));
                    }
                    for (String key : numDict.keySet()) {
                        if (workingString.substring(pointer, i + 1).contains(key)) {
                            keyString = keyString + Integer.toString(numDict.get(key));
                            pointer = i;
                        }
                    }
                }

                System.out.println("Working String: " + workingString);
                System.out.println("Key String: " + keyString);

                // first digit
                for (int i = 0; i < keyString.length(); i++) {
                    if (Character.isDigit(keyString.charAt(i))) {
                        digitOne = keyString.charAt(i);
                        break;
                    }
                }

                // second digit
                for (int i = keyString.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(keyString.charAt(i))) {
                        digitTwo = keyString.charAt(i);
                        break;
                    }
                }
                
                //calibration value
                lineAnswer = Character.toString(digitOne).concat(Character.toString(digitTwo));

                System.out.println("Calibration value: " + lineAnswer);
                total += Integer.valueOf(lineAnswer);
                System.out.println("Total: " + total);
            }
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}