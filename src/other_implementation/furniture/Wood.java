package other_implementation.furniture;

import java.util.ArrayList;

public class Wood extends Furniture {

    public Wood(String furnitureName) {
        super(furnitureName);
    }

    public String toString(){
        return furnitureName + ";";
    }
}
