public class Factory extends Node {
    int index;

    String tag;

    public Factory(int index, String tag, int x, int y) {
        super(x, y);
        this.index = index;
        this.tag = tag;
    }
}
