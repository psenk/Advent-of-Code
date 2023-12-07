import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class DayFour implements Day {

    // 9721255

    HashMap<Integer, Integer> dataSet = new HashMap<>();
    HashMap<Integer, Integer> numCards = new HashMap<>();
    ArrayList<Integer> cardQueue = new ArrayList<>();
    // int totalPoints = 0; // 1st star
    int gameNumber = 0;
    int totalCards = 0;

    public int scanCard(String lottoCard) {
        int numMatches = 0;
        int winningNumbersIndex = lottoCard.indexOf(":") + 1;
        int cardNumbersIndex = lottoCard.indexOf("|") + 1;
        HashSet<Integer> winningNumSet = new HashSet<>();
        // winning numbers
        String[] winningNums = lottoCard.substring(winningNumbersIndex, cardNumbersIndex - 1).strip()
                .split(" ");
        // TODO: get rid of the "" elements in winningNums before loop
        for (int i = 0; i < winningNums.length; i++) {
            if (winningNums[i] != "") {
                winningNumSet.add(Integer.valueOf(winningNums[i]));
            }
        }

        // card numbers
        String[] cardNumbers = lottoCard.substring(cardNumbersIndex).strip().split(" ");
        for (int i = 0; i < cardNumbers.length; i++) {
            if (cardNumbers[i] != "") {
                if (winningNumSet.contains(Integer.valueOf(cardNumbers[i]))) {
                    numMatches++;
                    // cardPoints = (cardPoints == 0) ? 1 : cardPoints << 1; // 1st star
                    // System.out.println(totalPoints);
                    // System.out.print(Integer.valueOf(cardNumbers[i]) + " ");
                }
            }
        }
        // totalPoints += cardPoints; // 1st star
        // System.out.println();
        return numMatches;
    }

    public void compute() {
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Four.txt"))) {
            // while (sc.hasNextLine()) { // 1st star
            // int cardPoints = 0; // 1st star

            // scanning the input file
            while (sc.hasNextLine()) {
                gameNumber++;
                String lottoCard = sc.nextLine();
                dataSet.put(gameNumber, scanCard(lottoCard));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        /*
         * 2nd star testing code
         * int count = 0;
         * while (count < 10) {
         * count++;
         */

        int card = 1;
        int matches = 0;
        do {
            matches = dataSet.get(card);
            if (numCards.keySet().contains(card)) {
                numCards.put(card, numCards.get(card) + 1);
            } else {
                numCards.put(card, 1);
            }
            System.out.println("Card: " + card + " Matches: " + matches);
            System.out.println(numCards.get(card) + " loops");
            for (int i = 0; i < numCards.get(card); i++) {
                // System.out.println("Loop # " + (i + 1));
                for (int j = 1; j <= matches; j++) {
                    if (numCards.keySet().contains(card + j)) {
                        numCards.put(card + j, numCards.get(card + j) + 1);
                    } else {
                        numCards.put(card + j, 1);
                    }
                }
            }
            System.out.println();
            card++;
        } while (dataSet.get(card) != null);
        /*
         * cardQueue.add(1);
         * while (!cardQueue.isEmpty()) {
         * 
         * int card = cardQueue.remove(0);
         * int matches = dataSet.get(card);
         * if ((card + matches) > dataSet.size()) {
         * matches = dataSet.size();
         * }
         * if (numCards.keySet().contains(card)) {
         * numCards.put(card, numCards.get(card) + 1);
         * } else {
         * numCards.put(card, 1);
         * }
         * 
         * System.out.println("Card # " + card + " has " + matches + " matches.");
         * for (int i = 1; i <= matches; i++) {
         * System.out.println("Adding card " + (card + i) + " to queue.");
         * cardQueue.add(card + i);
         * }
         * Collections.sort(cardQueue);
         * System.out.println("There are " + cardQueue.size() + " cards in queue.");
         * }
         */

        // System.out.println("Total points: " + totalPoints); // 1st star
        for (int a : numCards.keySet()) {
            System.out.println("Number of " + a + " instances: " + numCards.get(a));
            totalCards += numCards.get(a);
        }
        System.out.println("Total cards: " + totalCards);
    }
}