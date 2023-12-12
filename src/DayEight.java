import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import util.Tuple;
import util.Pointer;

public class DayEight implements Day {

    String map;
    HashMap<String, Tuple<String, String>> areaMap = new HashMap<>();
    int steps = 0;
    ArrayList<Pointer<String>> trackedLocations = new ArrayList<>();

    private void travel() {
        int mapPointer = 0;

        for (String location : areaMap.keySet()) {
            if (location.toString().charAt(2) == 'A') {
                trackedLocations.add(new Pointer<String>(location));
            }
        }
        int count = 0;
        while (count != trackedLocations.size()) {
            for (int i = 0; i < trackedLocations.size(); i++) {
                if (trackedLocations.get(i).getLocation().charAt(2) != 'Z') {
                    i = -1;
                    // move all pointers
                    if (map.charAt(mapPointer) == 'L') {
                        for (Pointer<String> c2 : trackedLocations) {
                            System.out.println("Moving Left.");
                            // currentLocation = areaMap.get(currentLocation).getKey();
                            c2.setLocation(areaMap.get(c2.getLocation()).getKey());
                            steps++;
                            count = 0;
                        }
                    }
                    else {
                        for (Pointer<String> c2 : trackedLocations) {
                            System.out.println("Moving Right.");
                            c2.setLocation(areaMap.get(c2.getLocation()).getValue());
                            steps++;
                            count = 0;
                        }
                    }
                    mapPointer++;
                    if (mapPointer == map.length()) {
                        mapPointer = 0;
                    }
                }
                else {
                    count++;
                }
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
                String locationName = line.substring(0, 3);
                Tuple<String, String> locationsOut = new Tuple<>(line.substring(7, 10), line.substring(12, 15));
                areaMap.put(locationName, locationsOut);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }

        travel();
        System.out.println("Steps from A to Z: " + steps);
    }
}
