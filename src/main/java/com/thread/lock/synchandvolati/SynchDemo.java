package com.thread.lock.synchandvolati;

/**
 * 测试synchronized 和 volatile 区别
 * @author
 */
public class SynchDemo extends  Thread{
    public void synMothed(){
        synchronized (this){
            int p = 2;
            for (int i = 0; i < p; i++) {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()
                            + " SynMothed demo " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void run(){

    }
}
