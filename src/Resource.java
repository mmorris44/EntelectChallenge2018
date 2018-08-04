import java.util.ArrayList;

public class Resource {

    String tag;
    ArrayList<Factory> factories = new ArrayList<>();
    ArrayList<Mine> mines = new ArrayList<>();

    public Resource (String tag) {
        this.tag = tag;
    }

}
