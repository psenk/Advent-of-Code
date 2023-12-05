import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo implements Day {

    // [12, 13, 14]
    // [red, green, blue]
    final int[] TOTAL_CUBES = { 12, 13, 14 };
    int sumOfIDs;

    public class Game {
        private static int id = 0;
        private ArrayList<int[]> draws = new ArrayList<>();

        public Game() {
            id++;
        }

        public int getId() {
            return id;
        }

        public ArrayList<int[]> getDrawsList() {
            return draws;
        }

        public void addToDraws(int[] draw) {
            draws.add(draw);
        }

    }

    public void compute() {
        try (Scanner sc = new Scanner(new File(".\\Day Two.txt"))) {
            while (sc.hasNextLine()) {
                Game game = new Game();
                // for testing: String scanInput = "Game 4: 9 red, 3 green; 3 green, 8 red, 6
                // blue; 12 blue, 4 green, 6 red; 4 green, 18 blue, 11 red; 9 blue, 2 green, 3
                // red; 14 blue, 7 red";
                String scanInput = sc.nextLine();
                String[] draws = scanInput.substring(scanInput.indexOf(":") + 2, scanInput.length()).split(";");

                System.out.println("Game: " + game.getId() + " - Dice Draws: " + draws.length);
                System.out.println("Line: \"" + scanInput + "\"");

                // adding draws to game for evaluation
                for (int i = 0; i < draws.length; i++) {
                    draws[i] = draws[i].strip();
                    int[] draw = new int[3];
                    System.out.print("Draw #" + (i + 1) + ": ");
                    String[] gameString = draws[i].split(" ");

                    for (int j = 1; j <= gameString.length; j += 2) {
                        gameString[j] = gameString[j].replace(",", "");

                        String colorIn = gameString[j];
                        int amt = Integer.parseInt(gameString[j - 1]);

                        if (colorIn.contains("red")) {
                            draw[0] += amt;
                        } else if (colorIn.contains("green")) {
                            draw[1] += amt;
                        } else {
                            draw[2] += amt;
                        }
                        System.out.print("" + gameString[j - 1] + " " + gameString[j] + " ");
                    }
                    System.out.println();
                    game.addToDraws(draw);
                }

                // scanning the draws in the games, comparing against maximum allowable constant
                boolean winnable = true;
                for (int i = 0; i < game.getDrawsList().size(); i++) {
                    if (game.getDrawsList().get(i)[0] > TOTAL_CUBES[0] ||
                            game.getDrawsList().get(i)[1] > TOTAL_CUBES[1] ||
                            game.getDrawsList().get(i)[2] > TOTAL_CUBES[2]) {
                        System.out.println("Game LOST!\n");
                        winnable = false;
                        break;
                    }
                }
                if (winnable) {
                    sumOfIDs += game.getId();
                    System.out.println("Game WON!  Adding " + game.getId() + " points!\n");
                }
            }
            System.out.println("Sum of all winning IDs: " + sumOfIDs);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}