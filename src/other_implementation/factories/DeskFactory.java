package other_implementation.factories;

import other_implementation.Ikea;
import other_implementation.furniture.*;
import other_implementation.resources.ResourceContainer;

public class DeskFactory extends Factory {

    private Leg leg;
    private Shelf shelf;
    private Top top;

    public DeskFactory(String nameType){
        super(nameType);
        this.leg=null;
        this.shelf=null;
        this.top=null;
    }

    @Override
    public Furniture create() {
        return new Desk(this.leg, this.shelf, this.top, this.getFurnitureName(this.nameType));
    }

    @Override
    public void addResource(Furniture furniture) {
        ResourceContainer.legs.pushResource(furniture);
    }

    public void clear(){
        this.shelf=null;
        this.top=null;
        this.leg=null;
    }

    @Override
    public void run() {
        while(ResourceContainer.desks.getSize() < Ikea.maxDesks) {
            if (shelf == null) {
                if (!ResourceContainer.shelves.isEmpty()) {
                    Shelf auxShelf = (Shelf) ResourceContainer.shelves.getFirst();
                    boolean lockShelf = auxShelf.lock.tryLock();
                    if (lockShelf) {
                        shelf = auxShelf;
                        ResourceContainer.shelves.removeFurniture(auxShelf);
                        printLocked(shelf.toString());
                    } else {
                        printNotLocked(auxShelf.toString());
                    }
                    auxShelf.lock.unlock();
                }
            }
            if (leg == null) {
                if (!ResourceContainer.legs.isEmpty()) {
                    Leg auxLeg = (Leg) ResourceContainer.legs.getFirst();
                    boolean lockLeg = auxLeg.lock.tryLock();
                    if (lockLeg) {
                        leg = auxLeg;
                        ResourceContainer.legs.removeFurniture(auxLeg);
                        printLocked(leg.toString());
                    } else {
                        printNotLocked(auxLeg.toString());
                    }
                    auxLeg.lock.unlock();
                }
            }
            if (top == null) {
                if (!ResourceContainer.tops.isEmpty()) {
                    Top auxTop = (Top) ResourceContainer.tops.getFirst();
                    boolean lockTop = auxTop.lock.tryLock();
                    if (lockTop) {
                        top = auxTop;
                        ResourceContainer.tops.removeFurniture(auxTop);
                        printLocked(top.toString());
                    } else {
                        printNotLocked(auxTop.toString());
                    }
                    auxTop.lock.unlock();
                }
            }
            if (shelf != null && leg != null && top != null) {
                Desk newDesk = (Desk) create();
                addResource(newDesk);
                this.count++;
                printCreated(newDesk.toString());
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

