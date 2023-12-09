import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DaySix implements Day {

    ArrayList<Integer> inputs = new ArrayList<>();
    final int NUM_RACES = 4;

    @Override
    public void compute() {
        int winCount = 0;
        int margin = 1;
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Six.txt"))) {
            while (sc.hasNextLine()) {
                sc.next();
                for (int i = 0; i < NUM_RACES; i++) {
                    inputs.add(sc.nextInt());
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        /* int[] raceTimes = new int[inputs.size() / 2]; // 1st star
        int[] distances = new int[inputs.size() / 2]; 
        for (int i = 0; i < raceTimes.length; i++) {
            raceTimes[i] = inputs.get(i);
            distances[i] = inputs.get(i + raceTimes.length);
        }*/
        String time = "";
        String distance = "";
        for (int i = 0; i < inputs.size() / 2; i++) {
            time += String.valueOf(inputs.get(i));
            distance += String.valueOf(inputs.get(i + (inputs.size() / 2)));
        }
        long raceTime = Long.valueOf(time);
        long raceDistance = Long.valueOf(distance);

        for (int i = 0; i <= raceTime; i++) {
            //     ms/s
            if ((i * (raceTime - i)) > raceDistance) {
                winCount++;
            }          
        }
        //System.out.println(margin);
        System.out.println(winCount);
    }
}
