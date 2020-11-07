package workers;

import main.Production;
import resources.DeskResource;
import resources.Leg;
import resources.Wood;


public class LegCarver extends Worker {


    public LegCarver(Production prod) {
        super(prod);
    }

    public void specificWork() {
        if (resources.size() > 0) {
            try {
                fetchResource(Wood.class);
                resources.add(new Leg());
                System.out.println("Beautifully carved a leg from the stick that I got, legs: " + prod.getLegs() + ". Remaining wood: " + prod.getWood());
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                specificWork();
            }


        }
    }
}
