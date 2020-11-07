package other_implementation.factories;

import other_implementation.furniture.Furniture;
import other_implementation.resources.Resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Factory implements Runnable {

    public final Lock lock = new ReentrantLock();
    protected boolean full;
    private int count;

    public Factory(){
        this.full = false;
        this.count = 0;
    }

    public void setFull(){
        this.full = true;
    }

    public String getFurnitureName(String type){
        return type + "_" + this.count;
    }

    public abstract Furniture create();
    public abstract void addResource(Furniture furniture);
    public abstract void gatherResources();
}
