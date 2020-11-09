package workers;

import main.Production;
import resources.*;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Worker {
    protected Production prod;
    protected CopyOnWriteArrayList<DeskResource> resources;

    public Worker (Production prod) { this.prod = prod; }

    public void setResource(CopyOnWriteArrayList<DeskResource> res) {
        this.resources = res;
    }

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
            for(DeskResource dr : resources) {
                if(cls.isInstance(dr)) {
                    resources.remove(dr);
                    return dr;
                }
            }
        }
        return null;
    }

    public abstract void specificWork();

}
