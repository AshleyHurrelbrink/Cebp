package workers;

import main.Production;
import resources.Wood;

public class WoodCutter extends Worker {

    public WoodCutter(Production prod) {
        super(prod);
    }

    public void specificWork() {
        try {
            resources.add(new Wood());
            System.out.println("Just chopped another tree, wood: " + prod.getWood());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            specificWork();
        }
    }
}
