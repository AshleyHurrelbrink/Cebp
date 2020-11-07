package other_implementation.factories;

import other_implementation.furniture.Furniture;
import other_implementation.furniture.Leg;
import other_implementation.furniture.Wood;
import other_implementation.resources.ResourceContainer;

import java.util.TimerTask;
import java.util.Timer;

public class LegFactory extends Factory {

    private Wood wood1, wood2;
    Timer timer;

    public LegFactory(){
        this.wood1 = null;
        this.wood2 = null;
        this.timer = new Timer("leg");
        this.startTimer();
    }

    @Override
    public Furniture create() {
        return new Leg(wood1, wood2, this.getFurnitureName("leg"));
    }

    @Override
    public void addResource(Furniture furniture) {
        ResourceContainer.legs.pushResource(furniture);
    }

    @Override
    public void gatherResources() {

    }

    @Override
    public void run() {
    }

    public void clear(){
        this.wood1 = null;
        this.wood2 = null;
    }

    private void startTimer(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean lockWood;
                Wood auxWood;
                if(wood1 == null){
                    if(!ResourceContainer.wood.isEmpty()){
                        auxWood = (Wood) ResourceContainer.wood.getFirst();
                        lockWood = auxWood.lock.tryLock();
                        if(lockWood){
                            wood1 = auxWood;
                            ResourceContainer.wood.removeFurniture(auxWood);
                        }
                        auxWood.lock.unlock();
                    }
                }
                if(wood2 == null){
                    if(!ResourceContainer.wood.isEmpty()){
                        auxWood = (Wood) ResourceContainer.wood.getFirst();
                        lockWood = auxWood.lock.tryLock();
                        if(lockWood){
                            wood2= auxWood;
                            ResourceContainer.wood.removeFurniture(auxWood);
                        }
                        auxWood.lock.unlock();
                    }
                }
                if(wood1 != null && wood2!= null){
                    Leg newLeg = (Leg) create();
                    addResource(newLeg);
                    clear();
                }
            }
        }, 0, 1000);
    }


}
