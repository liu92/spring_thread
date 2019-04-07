package com.thread.lock.synchandvolati;

/**
 *  测试synchronized 和 volatile 区别
 *  @author
 */
public class VolatileDemo extends Thread {
     public  static  volatile  int count;

     public  void  vDemo(){
         int td = 5;
         for (int i = 0; i < td; i++) {
             count++;
         }
         System.out.println("count=" + count);
     }

     @Override
     public void  run(){
         vDemo();
     }


}
