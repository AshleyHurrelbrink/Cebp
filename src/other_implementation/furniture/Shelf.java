package other_implementation.furniture;

import java.util.ArrayList;

public class Shelf extends Furniture {

    private ArrayList<Wood> wood;

    public Shelf(ArrayList<Wood> wood, String furnitureName) {
        super(furnitureName);
        this.wood = wood;
    }

    public String toString(){
        String components="";
        for(Wood w : wood){
            components.concat(w.furnitureName+", ");
        }
        return furnitureName+ ": "+ components + ";";
    }
}
