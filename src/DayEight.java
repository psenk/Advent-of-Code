import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import util.Tuple;

public class DayEight implements Day {

    String map;
    HashMap<String, Tuple<String, String>> areaMap = new HashMap<>();
    int steps = 0;

    private void travel() {
        int mapPointer = 0;
        String currentLocation = "AAA";
        while (!currentLocation.equals("ZZZ")) {
            System.out.println("Current Location: " + currentLocation);
            if (map.charAt(mapPointer) == 'R') {
                System.out.println("Moving Right.");
                currentLocation = areaMap.get(currentLocation).getValue();
                steps++;
            }
            else if (map.charAt(mapPointer) == 'L') {
                System.out.println("Moving Left.");
                currentLocation = areaMap.get(currentLocation).getKey();
                steps++;
            }
            mapPointer++;
            if (mapPointer == map.length()) {
                mapPointer = 0;
            }
        }
    }

    @Override
    public void compute() {
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Eight.txt"))) {
            map = sc.nextLine();
            sc.nextLine();
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String locationName = line.substring(0,3);
                Tuple<String, String> locationsOut = new Tuple<>(line.substring(7, 10), line.substring(12, 15));
                areaMap.put(locationName, locationsOut);
                //.put(locationName, locationsOut);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }

        travel();
        System.out.println("Steps from AAA to ZZZ: " + steps);
    }
}
