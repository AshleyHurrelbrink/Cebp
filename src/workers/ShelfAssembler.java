package workers;

import main.Production;
import resources.*;

import java.util.ArrayList;
import java.util.Random;

public class ShelfAssembler extends Worker {
    private ArrayList<Wood> savedWood = new ArrayList<>(10);

    public ShelfAssembler(Production prod) {
        super(prod);
    }


    public int getSavedWood() {
        return savedWood.size();
    }
    @Override
    public void specificWork() {
        if (savedWood.size() == 10) {
            try {
                resources.add(new Shelf());
                savedWood.clear();
                System.out.println("Assembled a new shelf from the saved wood. Shelves: " + prod.getShelves());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  catch (ArrayIndexOutOfBoundsException e) {
                specificWork();
            }
        }
        else if (savedWood.size() < 10 && resources.size() > 2) {
            try {
                Wood saved = (Wood)fetchResource(Wood.class);
                savedWood.add(saved);
                System.out.println("Gathering wood for a shelf. Current saved: "+ savedWood.size() + ". Remaining wood: " + prod.getWood() + "; Shelves: " + prod.getShelves());
                Thread.sleep(2050);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  catch (ArrayIndexOutOfBoundsException e) {
                specificWork();
            }
        }
    }
}