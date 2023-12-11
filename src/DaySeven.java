import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import util.Tuple;
import util.Pair;

public class DaySeven implements Day {

    HashMap<String, Integer> gamesList = new HashMap<>();
    String gameHand = "";
    int gameBid = 0;
    // A K Q J T 9 8 7 6 5 4 3 2
    String CARD_ORDER = "AKQJT98765432";
    ArrayList<Pair<Integer, char[]>> hands = new ArrayList<>();
    long totalWinnings = 0;

    public void handAnalyzer(String hand) {
        char[] cards = hand.toCharArray();
        HashMap<Character, Integer> currentHand = new HashMap<>();

        // put letters into dict
        for (int i = 0; i < gameHand.length(); i++) {
            if (currentHand.keySet().contains(cards[i])) {
                currentHand.put(cards[i], currentHand.get(cards[i]) + 1);
            } else {
                currentHand.put(cards[i], 1);
            }
        }

        // identify hands
        if (currentHand.size() == 1) {
            // System.out.print("Five of a Kind - ")
            hands.add(new Tuple<Integer, char[]>(1, cards));
        } else if (currentHand.size() == 2) {
            if (currentHand.values().contains(1)) {
                // System.out.print("Four of a Kind - ");
                hands.add(new Tuple<Integer, char[]>(2, cards));
            } else {
                hands.add(new Tuple<Integer, char[]>(3, cards));
                // System.out.print("Full House - ");
            }
        } else if (currentHand.size() == 3) {
            if (currentHand.values().contains(3)) {
                // System.out.print("Three of a Kind - ");
                hands.add(new Tuple<Integer, char[]>(4, cards));
            } else {
                // System.out.print("Two Pair - ");
                hands.add(new Tuple<Integer, char[]>(5, cards));
            }
        } else if (currentHand.size() == 4) {
            // System.out.print("One Pair - ");
            hands.add(new Tuple<Integer, char[]>(6, cards));
        } else {
            // System.out.print("High Card - ");
            hands.add(new Tuple<Integer, char[]>(7, cards));
        }
    }

    public int compareHands(int i, int j) {
        // return strongest hand
        char[] hand1 = hands.get(i).getValue();
        char[] hand2 = hands.get(j).getValue();

        for (int k = 0; k < 5; k++) {
            if (CARD_ORDER.indexOf(hand1[k]) < CARD_ORDER.indexOf(hand2[k])) {
                return i;
            }
            if (CARD_ORDER.indexOf(hand1[k]) > CARD_ORDER.indexOf(hand2[k])) {
                return j;
            }
        }
        return i;
    }

    public void sortHands(ArrayList<Pair<Integer, char[]>> hands) {

        // sorting by hand strength first
        for (int i = 0; i < hands.size() - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < hands.size(); j++) {
                if (hands.get(j).getKey() < hands.get(smallest).getKey()) {
                    smallest = j;
                }
            }
            Collections.swap(hands, i, smallest);
        }

        // sorting hands
        for (int i = 0; i < hands.size() - 1; i++) {
            int strongest = i;
            for (int j = i + 1; j < hands.size(); j++) {
                int count = j;
                if (hands.get(i).getKey() == hands.get(j).getKey()) {
                    while (count < hands.size() && hands.get(i).getKey() == hands.get(count).getKey()) {
                        strongest = compareHands(j, strongest);
                        count++;
                    }
                }
            }
            Collections.swap(hands, i, strongest);
        }
    }

    @Override
    public void compute() {
        String line;
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Seven.txt"))) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] lineArray = line.split(" ");
                gamesList.put(lineArray[0], Integer.valueOf(lineArray[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (String cardHand : gamesList.keySet()) {
            gameHand = cardHand.toString();
            // System.out.print(gameHand + " ");
            handAnalyzer(gameHand);
        }
        sortHands(hands);

/*         for (Pair<Integer, char[]> pair : hands) {
            System.out.print(pair.getKey() + " ");
            for (char card : pair.getValue()) {
                System.out.print(card);
            }
            System.out.println();
        } */
        int handsSize = 1000;
        String hand = "";
        for (int i = 0; i < hands.size(); i++) {
            for (char c : hands.get(i).getValue()) {
                hand += c;
            }
            int add = gamesList.get(hand) * handsSize;
            totalWinnings += add;
            hand = "";
            handsSize--;
        }

        /*
         * A K Q J T 9 8 7 6 5 4 3 2
         * Five of a kind - AAAAA
         * Four of a Kind - AA2AA
         * Full House - 66777
         * Three of a Kind - TTT98
         * Two Pair - 66884
         * One Pair - 77123
         * High Card - 23456
         */
        System.out.println(totalWinnings);
    }
}
