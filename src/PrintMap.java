import java.util.ArrayList;

public class PrintMap {
    ArrayList<ArrayList<String>> mapGrid;

    public PrintMap(InputParser input) {
        mapGrid = new ArrayList<ArrayList<String>>(input.mapHeight);
        for (int y=0; y<input.mapHeight; y++) {
            mapGrid.add(new ArrayList<String>(input.mapWidth));
            for (int x=0; x<input.mapWidth; x++){
                for (Mine m : input.mines) {
                    if (m.x ==1)
                }
                mapGrid.get(y).add("<>");
            }
        }

    }
}
