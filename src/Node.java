public class Node {
    int x, y;

    public Node (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance (Node node) {
        return Math.abs(x - node.x) + Math.abs(y  - node.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
