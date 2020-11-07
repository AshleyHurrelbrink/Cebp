package other_implementation.furniture;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Furniture {

    protected String furnitureName;
    public final Lock lock = new ReentrantLock();

    public Furniture(String furnitureName){
        this.furnitureName = furnitureName;
    }
}
