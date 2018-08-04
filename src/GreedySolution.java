import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class GreedySolution {
    public static ArrayList<Worker> workers;
    public static ArrayList<Mine> mines;
    public static ArrayList<Factory> factories;
    public static ArrayList<Resource> resources;
    public static Node workerStartNode = new Node(0, 0);
    ;

    public static void main(String[] args) {
        String filename = "maps/map_1.input";
        InputParser ip = new InputParser(filename);
        ip.read();
        ip.generateResources();

        workers = ip.workers;
        mines = ip.mines;
        factories = ip.factories;
        resources = ip.resources;

        ArrayList<WorkerTrack> tracks = new ArrayList<WorkerTrack>();

        for (Worker worker : workers) {
            tracks.add(new WorkerTrack(worker));
        }

        while (true) {
            Trip minTrip = null;
            for (WorkerTrack track : tracks) {
                Trip trip = track.getNextMinTrip();
                if (trip != null && (minTrip == null || trip.distance < minTrip.distance)) {
                    minTrip = trip;
                }
            }

            if (minTrip == null) break;
            else {
                WorkerTrack track = (WorkerTrack) minTrip.worker;
                track.appendTrip(minTrip);
            }
        }

        try {
            PrintWriter pr = new PrintWriter(new FileWriter("output"));

            int totdist = 0;
            for (WorkerTrack track : tracks) {
                totdist += track.cost;
            }
            System.out.println(totdist);

            for (WorkerTrack track : tracks) {
                pr.println(track);
            }

            pr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Trip {
    public Worker worker;
    public Node destination;
    public int distance;

    public Trip(Worker worker, Node destination) {
        this.worker = worker;
        this.destination = destination;
        this.distance = worker.node.getDistance(destination);
    }
}

class WorkerTrack extends Worker {
    public ArrayList<Node> nodes;
    HashMap<String, Boolean> resourceStore = new HashMap<String, Boolean>();
    public int cost = 0;
    public int store = 0;

    public WorkerTrack(int capacity) {
        super(capacity);
        this.node = GreedySolution.workerStartNode;
        this.nodes = new ArrayList<Node>();
        this.nodes.add(this.node);

        for (Resource resource : GreedySolution.resources) {
            resourceStore.put(resource.tag, false);
        }
    }

    public WorkerTrack(Worker worker) {
        this(worker.capacity);
    }

    public Trip getNextMinTrip() {
        Trip minTrip = null;

        for (int i = 0; i < GreedySolution.resources.size(); i++) {
            Resource resource = GreedySolution.resources.get(i);

            if (resourceStore.get(resource.tag) && this.store > 0) {
                ArrayList<Factory> factories = resource.factories;
                for (int j = 0; j < factories.size(); j++) {
                    Factory factory = factories.get(j);
                    if (minTrip == null || this.node.getDistance(factory) < minTrip.distance) {
                        minTrip = new Trip(this, factory);
                    }
                }
            } else if (this.store < this.capacity) {
                ArrayList<Mine> mines = resource.mines;
                for (int j = 0; j < mines.size(); j++) {
                    Mine mine = mines.get(j);
                    if (mine.supply > 0 && (minTrip == null || this.node.getDistance(mine) < minTrip.distance)) {
                        minTrip = new Trip(this, mine);
                    }
                }
            }
        }

        return minTrip;
    }

    public void appendTrip(Trip trip) {
        if (trip.destination instanceof Factory) {
            Factory factory = (Factory) trip.destination;

            this.node = factory;
            this.resourceStore.put(factory.tag, false);
            this.store -= 1;
        } else {
            Mine mine = (Mine) trip.destination;

            this.node = mine;
            this.resourceStore.put(mine.tag, true);
            mine.supply -= 1;
            this.store += 1;
        }

        this.cost += trip.distance;
        this.nodes.add(this.node);
    }

    public String toString() {
        String type = "";
        switch (this.capacity) {
            case 1:
                type = "M";
                break;
            case 3:
                type = "E";
                break;
            case 5:
                type = "H";
                break;
        }

        String ret = type + "|";

        for (int i = 0; i < nodes.size(); i++) {
            int index = 0;
            Node node = this.nodes.get(i);
            if (node instanceof Mine) {
                Mine mine = (Mine) node;
                index = mine.index;
            } else if (node instanceof Factory) {
                Factory factory = (Factory) node;
                index = factory.index;
            }

            if (i > 1) ret += ',';
            if (i > 0) ret += index;
        }

        return ret;
    }
}