import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import util.Tuple;

public class DayFour implements Day {
    boolean gameRunning = true;
    HashSet<Integer> winningNumSet = new HashSet<>();
    HashMap<String, Integer> numCards = new HashMap<>();
    //int totalPoints = 0; // 1st star
    int gameNumber = 0;

    public void compute() {
        try (Scanner sc = new Scanner(new File(".\\Day Four.txt"))) {
            while (gameRunning) {}
            //while (sc.hasNextLine()) { // 1st star
                // scanning the ticket
                //int cardPoints = 0; // 1st star
                gameNumber++;
                int numMatches = 0;
                String lottoTicket = sc.nextLine();

                // making dictionary for amt of times card is drawn
                if (!numCards.containsKey(lottoTicket)) {
                    numCards.put(lottoTicket, numMatches);
                }
                else {
                    numCards.put(lottoTicket, numMatches + 1);
                }
                int winningNumbersIndex = lottoTicket.indexOf(":") + 1;
                int ticketNumbersIndex = lottoTicket.indexOf("|") + 1;
                
                // winning numbers
                String[] winningNums = lottoTicket.substring(winningNumbersIndex, ticketNumbersIndex - 1).strip().split(" ");
                // TODO: get rid of the "" elements in winningNums before loop
                for (int i = 0; i < winningNums.length; i++) {
                    if (winningNums[i] != "") {
                        winningNumSet.add(Integer.valueOf(winningNums[i]));
                    }
                }

                // card numbers
                String[] ticketNumbers = lottoTicket.substring(ticketNumbersIndex).strip().split(" ");
                for (int i = 0; i < ticketNumbers.length; i++) {
                    if (ticketNumbers[i] != "") {
                        if (winningNumSet.contains(Integer.valueOf(ticketNumbers[i]))) {
                            numMatches++;
                            //cardPoints = (cardPoints == 0) ? 1 : cardPoints << 1; // 1st star
                            //System.out.println(totalPoints);
                            //System.out.print(Integer.valueOf(ticketNumbers[i]) + " ");
                        }
                    }
                }
                //totalPoints += cardPoints; // 1st star
                winningNumSet.clear();
                //System.out.println();
            }
            gameRunning = false;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Total points: " + totalPoints);
    }    
}
