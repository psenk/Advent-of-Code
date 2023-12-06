import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class DayFour implements Day {
    HashSet<Integer> winningNumSet = new HashSet<>();
    int totalPoints = 0;

    public void compute() {
        try (Scanner sc = new Scanner(new File(".\\Day Four.txt"))) {
            while (sc.hasNextLine()) {
                int cardPoints = 0;
                String lottoTicket = sc.nextLine();
                int winningNumbersIndex = lottoTicket.indexOf(":") + 1;
                int ticketNumbersIndex = lottoTicket.indexOf("|") + 1;
                String[] winningNums = lottoTicket.substring(winningNumbersIndex, ticketNumbersIndex - 1).strip().split(" ");
                // TODO: get rid of the "" elements in winningNums before loop
                for (int i = 0; i < winningNums.length; i++) {
                    if (winningNums[i] != "") {
                        winningNumSet.add(Integer.valueOf(winningNums[i]));
                    }
                }
                String[] ticketNumbers = lottoTicket.substring(ticketNumbersIndex).strip().split(" ");
                for (int i = 0; i < ticketNumbers.length; i++) {
                    if (ticketNumbers[i] != "") {
                        if (winningNumSet.contains(Integer.valueOf(ticketNumbers[i]))) {
                            cardPoints = (cardPoints == 0) ? 1 : cardPoints << 1;
                            //System.out.println(totalPoints);
                            //System.out.print(Integer.valueOf(ticketNumbers[i]) + " ");
                        }
                    }
                }
                totalPoints += cardPoints;
                winningNumSet.clear();
                //System.out.println();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Total points: " + totalPoints);
    }    
}
