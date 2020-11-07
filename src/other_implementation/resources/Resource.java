package other_implementation.resources;

import other_implementation.furniture.Furniture;

import java.util.ArrayList;

public class Resource {

    private ArrayList<Furniture> resource;

    public Resource(ArrayList<Furniture> resource){
        this.resource=resource;
    }

    public boolean checkForResource(){
        return false;
    }

    public boolean popResource(){
        return false;
    }

    public boolean pushResource(Furniture res){
        resource.add(res);
        return false;
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
}


