package main;

import resources.*;
import workers.Worker;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Production {
    public static int DESIRED_DESKS;
    private ArrayList<Worker> workers;
    private CopyOnWriteArrayList<DeskResource> resources = new CopyOnWriteArrayList<>();
    private static Production production = null;
    private Production() {
        workers = new ArrayList<>();
    }


    public static Production createProduction () {
        if (production != null) {
            return production;
        }
        return new Production();
    }

    public void addWorker (Worker w) {
        w.setResource(resources);
        workers.add(w);
    }

    public void removeWorker (Worker w) {
        workers.remove(w);
    }

    public void removeWorker (int index) {
        workers.remove(index);
    }

    public void startWorkline() {
        for (Worker worker : workers) {
            worker.doWork();
        }
    }
    public int getWood () {
        int count = 0;
        for (DeskResource res : resources) {
            if (res instanceof Wood) {
                count++;
            }
        }
        return count;
    }
    public int getDesks () {
        int count = 0;
        for (DeskResource res : resources) {
            if (res instanceof Desk) {
                count++;
            }
        }
        return count;
    }
    public int getLegs () {
        int count = 0;
        for (DeskResource res : resources) {
            if (res instanceof Leg) {
                count++;
            }
        }
        return count;
    }
    public int getTops () {
        int count = 0;
        for (DeskResource res : resources) {
            if (res instanceof Top) {
                count++;
            }
        }
        return count;
    }
    public int getShelves () {
        int count = 0;
        for (DeskResource res : resources) {
            if (res instanceof Shelf) {
                count++;
            }
        }
        return count;
    }

}
