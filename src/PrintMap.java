import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintMap {
    ArrayList<ArrayList<String>> mapGrid;
    int height;
    int width;

    public PrintMap(InputParser input) {
        mapGrid = new ArrayList<ArrayList<String>>(input.mapHeight);
        height = input.mapHeight;
        width = input.mapWidth;
        String space;

        for (int y = 0; y < input.mapHeight; y++) {
            mapGrid.add(new ArrayList<String>(input.mapWidth));
            for (int x = 0; x < input.mapWidth; x++) {
                space = "<>";
                for (Mine m : input.mines) {
                    if (m.x == y && m.y == x)
                        space = m.tag.toUpperCase();
                }
                if (space.equals("<>")) {
                    for (Factory f : input.factories) {
                        if (f.x == x && f.y == y) {
                            space = f.tag;
                        }
                    }
                }
                if (space.length() != 2) {
                    space = space + "!";
                }

                mapGrid.get(y).add(space);
            }
        }
    }

    public void print() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("map2"));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pr.print(mapGrid.get(y).get(x));
                }
                pr.println();
            }
            pr.close();
        }
        catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        String fileName = "maps/map_2.input";

        InputParser input = new InputParser(fileName);
        input.read();

        PrintMap map = new PrintMap(input);
        
        map.print();

    }

}
