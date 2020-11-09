package workers;

import main.Production;
import resources.*;

import java.util.ArrayList;
import java.util.Random;

public class TopDesigner extends Worker {
    private ArrayList<Wood> savedWood = new ArrayList<>(4);

    public TopDesigner(Production prod) {
        super(prod);
    }

    public int getSavedWood() {
        return savedWood.size();
    }
    @Override
    public void specificWork() {
        if (savedWood.size() == 4) {
            try {
                resources.add(new Top());
                savedWood.clear();
                System.out.println("Created a new top from the saved wood. Tops: " + prod.getTops());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                specificWork();
            }
        }
        else if (savedWood.size() < 4 && resources.size() > 1) {
            try {
                Wood saved = (Wood)fetchResource(Wood.class);
                if(saved == null) return;

                savedWood.add(saved);
                System.out.println("Gathering wood for a top. Current saved: "+ savedWood.size() + ". Remaining wood: " + prod.getWood() + "; Tops: " + prod.getTops());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ArrayIndexOutOfBoundsException e) {
                specificWork();
            }
        }
    }
}
