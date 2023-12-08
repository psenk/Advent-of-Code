import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DayFive implements Day {

    ArrayList<String> fullMap = new ArrayList<>();
    long closest = Long.MAX_VALUE;
    HashMap<Long, Long> foundSeeds = new HashMap<>();
    // seed -> soil -> fertilizer -> water -> light -> temperature -> humidity ->
    // location
    // int mapPointer = 0;
    /*
     * HashMap<Long, Long> seedToSoil = new HashMap<>();
     * HashMap<Long, Long> soilToFertilizer = new HashMap<>();
     * HashMap<Long, Long> fertilizerToWater = new HashMap<>();
     * HashMap<Long, Long> waterToLight = new HashMap<>();
     * HashMap<Long, Long> lightToTemperature = new HashMap<>();
     * HashMap<Long, Long> temperatureToHumidity = new HashMap<>();
     * HashMap<Long, Long> humidityToLocation = new HashMap<>();
     * ArrayList<HashMap<Long, Long>> maps = new ArrayList<>() {
     * {
     * add(seedToSoil);
     * add(soilToFertilizer);
     * add(fertilizerToWater);
     * add(waterToLight);
     * add(lightToTemperature);
     * add(temperatureToHumidity);
     * add(humidityToLocation);
     * }
     * };
     */

    /*
     * public String createMap(Scanner sc, String line) {
     * while (line != "") {
     * String[] mapping = line.split(" ");
     * long destStart = Long.valueOf(mapping[0]);
     * long srcStart = Long.valueOf(mapping[1]);
     * long range = Long.valueOf(mapping[2]);
     * 
     * // meat and potatoes
     * for (long i = 0; i < range; i++) {
     * maps.get(mapPointer).put(srcStart + i, destStart + i);
     * System.out.println("Added map point.");
     * }
     * 
     * System.out.println("Line " + line + " created.");
     * if (sc.hasNextLine()) {
     * line = sc.nextLine();
     * } else {
     * break;
     * }
     * }
     * System.out.println("Map created.");
     * return line;
     * }
     */
    // list1 = 1 2 3 4 5 6 7 8 9
    //
    // num: 3

    /*
     * public void getSeeds(String line) {
     * String input = line.substring(line.indexOf(":") + 2);
     * String[] tempSeeds = input.split(" ");
     * 
     * for (int i = 0; i < tempSeeds.length; i += 2) {
     * long rangeStart = Long.valueOf(tempSeeds[i]);
     * long rangeLength = Long.valueOf(tempSeeds[i + 1]);
     * for (long j = 0; j < rangeLength; j++) {
     * seeds.add(rangeStart + j);
     * }
     * }
     * System.out.println("Seeds List Created.");
     * }
     */

    public void scanSeeds(String line) {

        String input = line.substring(line.indexOf(":") + 2);
        String[] tempSeeds = input.split(" ");

        for (int i = 0; i < tempSeeds.length; i += 2) {
            System.out.println("new seeds");
            long rangeStart = Long.valueOf(tempSeeds[i]);
            long rangeLength = Long.valueOf(tempSeeds[i + 1]);
            for (long j = 0; j < rangeLength; j++) {
                long num = 0;
                System.out.println("checking seed " + (rangeStart + j));
                if (foundSeeds.keySet().contains(rangeStart + j)) {
                    num = foundSeeds.get(rangeStart + j);
                    System.out.println("seed exists");
                }
                else {
                    num = scanSeed(rangeStart + j);
                    foundSeeds.put(rangeStart + j, num);
                }
                if (num < closest) {
                    System.out.println("Closest changed to " + num);
                    closest = num;
                }
            }
        }
        System.out.println("Closest location: " + closest);
    }

    public long scanSeed(long seed) {
        for (int i = 1; i < fullMap.size(); i++) {
            String line = fullMap.get(i);
            if (line == "") {
                i += 2;
                line = fullMap.get(i);
            }
            String[] mapping = line.split(" ");
            long destStart = Long.valueOf(mapping[0]);
            long srcStart = Long.valueOf(mapping[1]);
            long range = Long.valueOf(mapping[2]);

            if (seed >= srcStart && seed < (srcStart + range)) {
                seed = destStart + (seed - srcStart);
                while (line != "" && i + 1 < fullMap.size()) {
                    i++;
                    line = fullMap.get(i);
                }
                i--;
            }
        }
        return seed;
    }

    @Override
    public void compute() {
        try (Scanner sc = new Scanner(new File("src\\inputs\\Day Five.txt"))) {
            while (sc.hasNextLine()) {
                fullMap.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        scanSeeds(fullMap.get(0));
    }

    /*
     * This code takes INSANELY LONG. Trying something different.
     * 
     * long closest = Long.MAX_VALUE;
     * for (long seed : seeds) {
     * 
     * long pointer = seed;
     * if (seedToSoil.containsKey(seed)) {
     * pointer = seedToSoil.get(seed);
     * }
     * if (soilToFertilizer.containsKey(pointer)) {
     * pointer = soilToFertilizer.get(pointer);
     * }
     * if (fertilizerToWater.containsKey(pointer)) {
     * pointer = fertilizerToWater.get(pointer);
     * }
     * if (waterToLight.containsKey(pointer)) {
     * pointer = waterToLight.get(pointer);
     * }
     * if (lightToTemperature.containsKey(pointer)) {
     * pointer = lightToTemperature.get(pointer);
     * }
     * if (temperatureToHumidity.containsKey(pointer)) {
     * pointer = temperatureToHumidity.get(pointer);
     * }
     * if (humidityToLocation.containsKey(pointer)) {
     * pointer = humidityToLocation.get(pointer);
     * }
     * System.out.println("Current closest location: " + closest);
     * if (pointer < closest) {
     * closest = pointer;
     * }
     * 
     * }
     */
    // System.out.println("Final closest location: " + closest);
}