package workers;

import main.Production;
import resources.*;

import javax.annotation.Resource;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Worker {
    protected Production prod;
    protected CopyOnWriteArrayList<DeskResource> resources;
    public void setResource(CopyOnWriteArrayList<DeskResource> res) {
        this.resources = res;
    }
    public Worker (Production prod) { this.prod = prod; }


    public void doWork() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(prod.getDesks() < Production.DESIRED_DESKS) {
                    specificWork();
                }
            }
        }).start();
    }

    public DeskResource fetchResource(Class cls) throws InterruptedException {
        if (resources.size() > 0) {
            DeskResource dr = null;
            do {
                try {
                    dr = resources.get(new Random().nextInt(resources.size()));
                } catch (IllegalArgumentException ex) {
                    return null;
                }
            } while (!(cls.isInstance(dr)));
            resources.remove(dr);
            return dr;
        }
        return null;

    }

    public abstract void specificWork();

}
