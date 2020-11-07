package other_implementation.factories;

import other_implementation.Ikea;
import other_implementation.furniture.Furniture;
import other_implementation.furniture.Leg;
import other_implementation.furniture.Wood;
import other_implementation.resources.ResourceContainer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WoodFactory extends Factory{

    public WoodFactory(String nameType){
        super(nameType);
    }

    @Override
    public Furniture create() {
        return new Wood(this.getFurnitureName(this.nameType));
    }

    @Override
    public void addResource(Furniture furniture) {
        ResourceContainer.wood.pushResource(furniture);
    }

    public void clear(){
    }

    @Override
    public void run() {
        while(ResourceContainer.desks.getSize() < Ikea.maxDesks) {
            Wood newWood= (Wood)create();
            addResource(newWood);
            printCreated(newWood.toString());
            this.count++;

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
