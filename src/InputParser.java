import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InputParser {

    String filename = "";
    int mapHeight, mapWidth, minerCount, excavatorCount, haulerCount, workerCount, numberOfMines, numberOfFactories;
    long budget;

    ArrayList<Worker> workers = new ArrayList<>();
    ArrayList<Mine> mines = new ArrayList<>();
    ArrayList<Factory> factories = new ArrayList<>();
    ArrayList<Resource> resources = new ArrayList<>();

    public InputParser (String filename) {
        this.filename = filename;
    }

    public void read () {
        try {
            // Params
            System.out.println("Reading from file: " + filename);
            Scanner scanner = new Scanner(new File(filename));
            mapHeight = scanner.nextInt();
            mapWidth = scanner.nextInt();
            minerCount = scanner.nextInt();
            excavatorCount = scanner.nextInt();
            haulerCount = scanner.nextInt();
            workerCount = minerCount + excavatorCount + haulerCount;
            numberOfMines = scanner.nextInt();
            numberOfFactories = scanner.nextInt();
            budget = scanner.nextLong();
            scanner.nextLine();

            // Workers
            for (int i = 0; i < minerCount; ++i) {
                workers.add(new Worker(1));
            }
            for (int i = 0; i < excavatorCount; ++i) {
                workers.add(new Worker(3));
            }
            for (int i = 0; i < haulerCount; ++i) {
                workers.add(new Worker(5));
            }

            // Mines
            for (int mn = 0; mn < numberOfMines; ++mn) {
                int index = scanner.nextInt();
                String tag = scanner.next().toLowerCase();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int supply = scanner.nextInt();
                scanner.nextLine();

                mines.add(new Mine (index, tag, x, y, supply));
            }

            // Factories
            for (int f = 0; f < numberOfFactories; ++f) {
                int index = scanner.nextInt();
                String tag = scanner.next().toLowerCase();
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                factories.add(new Factory(index, tag, x, y));
            }

            scanner.close();
            System.out.println("Done reading");;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateResources () {
        // Make map
        HashMap<String, Resource> map = new HashMap<>();
        for (int i = 0; i < numberOfMines; ++i) {
            String tag = mines.get(i).tag;
            map.put(tag, new Resource(tag));
        }

        // Add mines
        for (Mine mine : mines) {
            String tag = mine.tag;
            Resource resource = map.get(tag);
            resource.mines.add(mine);
        }

        // Add factories
        for (Factory factory : factories) {
            String tag = factory.tag;
            Resource resource = map.get(tag);
            resource.factories.add(factory);
        }
    }

}
