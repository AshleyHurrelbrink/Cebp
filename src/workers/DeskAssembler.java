package workers;

import main.Production;
import resources.*;

public class DeskAssembler extends  Worker {


    public DeskAssembler(Production prod) {
        super(prod);
    }

    @Override
    public void specificWork() {
        if (prod.getLegs() >= 2 && prod.getTops() >= 1 && prod.getShelves() >= 1) {
            try {
                fetchResource(Leg.class);
                fetchResource(Leg.class);
                fetchResource(Shelf.class);
                fetchResource(Top.class);
                resources.add(new Desk());
                System.out.println("Desk assembled! Desks: " + prod.getDesks() + ". Remaining legs: " + prod.getLegs() + ". Shelves: " + prod.getShelves() + ". Tops: " + prod.getTops());
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }

