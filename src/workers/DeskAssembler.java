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
                DeskResource deskResource = null;
                do {
                    deskResource = (Leg)fetchResource(Leg.class);
                }while(deskResource == null);

                do {
                    deskResource = (Leg)fetchResource(Leg.class);
                }while(deskResource == null);

                do {
                    deskResource = (Shelf)fetchResource(Shelf.class);
                }while(deskResource == null);

                do {
                    deskResource = (Top)fetchResource(Top.class);
                }while(deskResource == null);

                resources.add(new Desk());
                System.out.println("Desk assembled! Desks: " + prod.getDesks() + ". Remaining legs: " + prod.getLegs() + ". Shelves: " + prod.getShelves() + ". Tops: " + prod.getTops());
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }

