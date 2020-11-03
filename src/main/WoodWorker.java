package main;

import java.util.Observable;

public class WoodWorker {
    public volatile int wood = 0;
    public volatile int legs = 0, tops = 0, shelves = 0, desks = 0;
    long start, end;

    public void manageSteps(int desiredDesks) {

        Thread w = new Thread(new Runnable() {
            @Override
            public void run() {
                while (desks < desiredDesks) {
                    wood++;
                    System.out.println("Gathering wood: " + wood);

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread leg = new Thread(new Runnable() {
            @Override
            public void run() {
                while (desks < desiredDesks) {
                    if (wood > 2) {
                        wood -= 2;
                        legs++;
                        System.out.println("Created leg. Remaining wood: " + wood + ". Legs: " + legs);

                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread shelf = new Thread(new Runnable() {
            @Override
            public void run() {
                while (desks < desiredDesks) {
                    if (wood > 8) {
                        wood -= 8;
                        shelves++;
                        System.out.println("Created shelf. Remaining wood: " + wood + ". Shelves: " + shelves);
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread top = new Thread(new Runnable() {
            @Override
            public void run() {
                while (desks < desiredDesks) {
                    if (wood > 1) {
                        wood -= 1;
                        tops++;
                        System.out.println("Created top. Remaining wood: " + wood + ". Tops: " + tops);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread desk = new Thread(new Runnable() {
            @Override
            public void run() {
                while (desks < desiredDesks) {
                    if (legs >= 2 && tops >= 1 && shelves >= 1) {
                        legs -= 2;
                        tops -= 1;
                        shelves -= 1;
                        desks++;
                        System.out.println("Built a desk: " + desks + ". Remaining tops: " + tops + ". Shelves: " + shelves + ". Legs: " + legs);
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (desks == desiredDesks) {
                    end = System.currentTimeMillis();

                    System.out.println("Total time: " + (end - start) + " ms");
                }
            }

        });

        start = System.currentTimeMillis();
        w.start();
        leg.start();
        shelf.start();
        top.start();
        desk.start();

    }
}

