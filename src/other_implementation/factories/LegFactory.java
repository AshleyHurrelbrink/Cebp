package other_implementation.factories;

import other_implementation.Ikea;
import other_implementation.furniture.Furniture;
import other_implementation.furniture.Leg;
import other_implementation.furniture.Wood;
import other_implementation.resources.ResourceContainer;
import java.util.ArrayList;

public class LegFactory extends Factory {

    private ArrayList<Wood> wood;
    private final int maxWood=2;

    public LegFactory(String nameType){
        super(nameType);
        wood=new ArrayList<Wood>();
    }

    @Override
    public Furniture create() {
        return new Leg(wood, this.getFurnitureName(this.nameType));
    }

    @Override
    public void addResource(Furniture furniture) {
        ResourceContainer.legs.pushResource(furniture);
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
                Leg newLeg = (Leg) create();
                addResource(newLeg);
                this.count++;
                printCreated(newLeg.toString());
                clear();
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
