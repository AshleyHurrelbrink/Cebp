package other_implementation.factories;

import other_implementation.furniture.Furniture;
import other_implementation.resources.Resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Factory implements Runnable {

    public final Lock lock = new ReentrantLock();
    protected boolean full;
    protected int count;
    protected String nameType;

    public Factory(String nameType){
        this.full = false;
        this.count = 0;
        this.nameType=nameType;
    }

    public void setFull(){
        this.full = true;
    }

    public String getFurnitureName(String type){
        return type + "_" + this.count;
    }

    public abstract Furniture create();
    public abstract void addResource(Furniture furniture);

    public void printLocked(String message){
        System.out.println(nameType +" LOCKED: " + message);
    }

    public void printNotLocked(String message){
        System.out.println(nameType +" NOT LOCKED: " + message);
    }

    public void printCreated(String message){
        System.out.println(nameType +" Created+Added: " + message);
    }
}
