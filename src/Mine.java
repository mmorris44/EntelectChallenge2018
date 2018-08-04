public class Mine extends Node {
    int index;

    String tag;
    int supply;

    public Mine(int index, String tag, int x, int y, int supply) {
        super(x, y);
        this.index = index;
        this.tag = tag;
        this.supply = supply;
    }
}
