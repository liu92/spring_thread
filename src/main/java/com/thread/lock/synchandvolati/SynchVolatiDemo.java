package com.thread.lock.synchandvolati;

public class SynchVolatiDemo {
    public static void main(String[] args) {
        VolatileDemo[] volatileDemos = new  VolatileDemo[5];
        int p = 5;
        for (int i = 0; i < p; i++) {
            volatileDemos[i] = new VolatileDemo();
        }

        for (int i = 0; i <p ; i++) {
            volatileDemos[i].start();
        }
    }
}
