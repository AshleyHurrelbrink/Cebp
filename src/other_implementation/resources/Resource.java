package other_implementation.resources;

import other_implementation.furniture.Furniture;

import java.util.ArrayList;

public class Resource {

    private ArrayList<Furniture> resource;

    public Resource(ArrayList<Furniture> resource){
        this.resource=resource;
    }


    public boolean isEmpty(){
        if(resource.isEmpty()) return true;
        return false;
    }

    public Furniture getFirst(){
        return resource.get(0);
    }

    public void removeFurniture(Furniture furniture){
        resource.remove(furniture);
    }

    public int getSize(){
        return resource.size();
    }

    public void pushResource(Furniture furniture){
        resource.add(furniture);
    }
}


