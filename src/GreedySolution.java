import java.util.ArrayList;

public class GreedySolution {

    public static void main (String[] args) {
        System.out.println("Hello world");

        String filename = "maps/map_1.input";
        InputParser ip = new InputParser(filename);
        ip.read();
        ip.generateResources();

        ArrayList<Worker> workers = ip.workers;
        ArrayList<Mine> mines = ip.mines;
        ArrayList<Factory> factories = ip.factories;
        ArrayList<Resource> resources = ip.resources;

    }

}
