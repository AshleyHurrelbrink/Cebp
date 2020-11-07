package other_implementation.resources;

import other_implementation.furniture.*;

import java.util.ArrayList;

public class ResourceContainer {

    private static ResourceContainer rc = null;

    public static Resource wood;
    public static Resource legs;
    public static Resource shelves;
    public static Resource tops;
    public static Resource desks;

    private ResourceContainer() {
        this.wood= new Resource(new ArrayList<Furniture>());
        this.legs= new Resource(new ArrayList<Furniture>());
        this.shelves= new Resource(new ArrayList<Furniture>());
        this.tops= new Resource(new ArrayList<Furniture>());
        this.desks= new Resource(new ArrayList<Furniture>());
    }

    public static ResourceContainer getResourceContainer(){
        if(rc == null)
            rc = new ResourceContainer();
        return rc;
    }
}
