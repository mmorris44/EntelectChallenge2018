import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputParser {

    String filename = "";
    int mapHeight, mapWidth, minerCount, excavatorCount, haulerCount, workerCount, numberOfMines, numberOfFactories, budget;

    public InputParser (String filename) {
        this.filename = filename;
    }

    public void read () {
        try {
            System.out.println("Reading from file: " + filename);
            Scanner scanner = new Scanner(new File(filename));
            mapHeight = scanner.nextInt();
            mapWidth = scanner.nextInt();
            minerCount = scanner.nextInt();
            excavatorCount = scanner.nextInt();
            haulerCount = scanner.nextInt();
            workerCount = scanner.nextInt();
            numberOfMines = scanner.nextInt();
            numberOfFactories = scanner.nextInt();
            budget = scanner.nextInt();
            scanner.nextLine();

            while(scanner.hasNextLine()) {
                int index = scanner.nextInt();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
