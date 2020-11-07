package other_implementation;

import other_implementation.factories.*;
import other_implementation.furniture.Shelf;
import other_implementation.furniture.Wood;
import other_implementation.resources.*;

import java.util.ArrayList;

public class Ikea {

    private Ikea ikea = null;
    public static final int maxDesks=4;
    private Ikea(){}

    public Ikea getIkeaStore(){
        if(this.ikea == null)
            this.ikea = new Ikea();
        return this.ikea;
    }

    public void startIkea(){

        ResourceContainer rc = ResourceContainer.getResourceContainer();

        ArrayList<Thread> factories = new ArrayList<>();
        factories.add(new Thread(new DeskFactory("desk")));
        factories.add(new Thread(new WoodFactory("wood")));
        factories.add(new Thread(new ShelfFactory("shelf")));
        factories.add(new Thread(new TopFactory("top")));
        factories.add(new Thread(new LegFactory("leg")));

        startThreads(factories);
    }

    public void startThreads(ArrayList<Thread> factories){
        for(Thread factory: factories){
            factory.start();
        }
    }

    public static void main(String[] args) {
        Ikea ikea=new Ikea();
        ikea.startIkea();
    }
}
