package main;


import other.ConfigReader;

public class Main {
    public static void main(String[] args) {
      WoodWorker mng = new WoodWorker();
      ConfigReader cfg = new ConfigReader("config.cfg");
      mng.manageSteps(cfg.getDesiredDesks());


    }

    public void runOrder(){
        WoodWorker mng = new WoodWorker();
        ConfigReader cfg = new ConfigReader("config.cfg");
        mng.manageSteps(cfg.getDesiredDesks());
    }
}
