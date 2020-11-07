package other_implementation.factories;

import other_implementation.Ikea;
import other_implementation.furniture.Furniture;
import other_implementation.furniture.Leg;
import other_implementation.furniture.Shelf;
import other_implementation.furniture.Wood;
import other_implementation.resources.ResourceContainer;

import java.util.ArrayList;

public class ShelfFactory extends Factory{


    private ArrayList<Wood> wood;
    private final int maxWood=4;

    public ShelfFactory(String nameType){
        super(nameType);
        wood=new ArrayList<Wood>();
    }

    @Override
    public Furniture create() {
        return new Shelf(wood, this.getFurnitureName(this.nameType));
    }

    @Override
    public void addResource(Furniture furniture) {
        ResourceContainer.shelves.pushResource(furniture);
    }

    public void clear(){
        this.wood=new ArrayList<Wood>();
    }

    @Override
    public void run() {
        while(ResourceContainer.desks.getSize() < Ikea.maxDesks){
            boolean lockWood;
            Wood auxWood;
            if(wood.size()<maxWood){
                if(!ResourceContainer.wood.isEmpty()){
                    auxWood = (Wood) ResourceContainer.wood.getFirst();
                    lockWood = auxWood.lock.tryLock();
                    if(lockWood){
                        wood.add(auxWood);
                        ResourceContainer.wood.removeFurniture(auxWood);
                        printLocked(wood.toString());
                    }
                    else{
                        printNotLocked(auxWood.toString());
                    }
                    auxWood.lock.unlock();
                }
            }
            else if(wood.size()==maxWood){
                Shelf newShelf = (Shelf) create();
                addResource(newShelf);
                this.count++;
                printCreated(newShelf.toString());
                clear();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
