package other_implementation.furniture;

import java.util.ArrayList;

public class Desk extends Furniture {

    private Leg leg;
    private Shelf shelf;
    private Top top;

    public Desk(Leg leg, Shelf shelf, Top top, String furnitureName) {
        super(furnitureName);
        this.leg=leg;
        this.shelf=shelf;
        this.top=top;
    }

    public String toString(){
        return furnitureName+ ": "+ leg.furnitureName + ", " + shelf.furnitureName + ", "+ shelf.furnitureName + ";";
    }
}
