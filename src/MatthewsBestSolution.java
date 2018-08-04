import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatthewsBestSolution {

    public static void main (String[] args) {
        System.out.println("Hello world");

        String filename = "maps/map_2.input";
        InputParser ip = new InputParser(filename);
        ip.read();
        ip.generateResources();

        ArrayList<Worker> workers = ip.workers;
        ArrayList<Mine> mines = ip.mines;
        ArrayList<Factory> factories = ip.factories;
        ArrayList<Resource> resources = ip.resources;

        ArrayList<WorkerRoute> wr = new ArrayList<>();
        for (Worker worker : workers) {
            wr.add(new WorkerRoute(worker));
        }

        WorkerRoute workMan = wr.get(0);
        for (Resource resource : resources) {
            for (Mine mine : resource.mines) {
                for (int i = 0; i < mine.supply; ++i) {
                    workMan.route.add(mine.index);
                    workMan.route.add(resource.factories.get(0).index);
                }
            }
        }

        try {
            PrintWriter pr = new PrintWriter(new FileWriter("output"));
            pr.write(workMan.toString());
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class WorkerRoute {
    Worker worker;
    ArrayList<Integer> route = new ArrayList<>();
    WorkerRoute(Worker worker) {
        this.worker = worker;
    }

    public String toString () {
        String type = "";
        switch (worker.capacity) {
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
        ret += route.get(0);
        for (int i = 1; i < route.size(); ++i) {
            ret += "," + route.get(i);
        }

        return ret;
    }
}
