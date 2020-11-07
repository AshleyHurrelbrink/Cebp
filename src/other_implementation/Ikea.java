package other_implementation;

import other_implementation.factories.*;
import other_implementation.furniture.Shelf;
import other_implementation.furniture.Wood;
import other_implementation.resources.*;

import java.util.ArrayList;

public class Ikea {

    private Ikea ikea = null;

    private Ikea(){}

    public Ikea getIkeaStore(){
        if(this.ikea == null)
            this.ikea = new Ikea();
        return this.ikea;
    }

    public void startIkea(){

        ResourceContainer rc = ResourceContainer.getResourceContainer();

        ArrayList<Thread> factories = new ArrayList<>();
        factories.add(new Thread(new DeskFactory()));
        factories.add(new Thread(new WoodFactory()));
        factories.add(new Thread(new ShelfFactory()));
        factories.add(new Thread(new TopFactory()));
        factories.add(new Thread(new LegFactory()));

        for(Thread factory: factories){
            factory.start();
        }
    }
}
