package com.thread.lock.conditionlock.one;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
     *  Condition 条件，并且比较Object 的wait(),notify(),notifyAll()和Condition 的await(),signal(),signalAll() 区别;
     * @ProjectName:    ConditionTest.java
     * @Package:        com.thread.lock.conditionlock
     * @ClassName:      ConditionTest
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/29 0029 17:44
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/29 0029 17:44
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class ConditionTest {

    private  static  Lock lock = new ReentrantLock();
    private  static  Condition condition = lock.newCondition();
    public static void main(String[] args) {
        ThreadB tb = new ThreadB("tb");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" start b");
            // 获取锁
            tb.start();

            System.out.println(Thread.currentThread().getName()+" block");
            //等待（阻塞）
            condition.await();
            System.out.println(Thread.currentThread().getName()+" continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }


    }

    /**
    * 线程类B
    */
    static  class ThreadB extends  Thread{
         ThreadB(String name){
           super(name);
       }


       @Override
       public  void  run(){
           lock.lock();
           try {
               System.out.println(Thread.currentThread().getName()+" workup other");
               // 唤醒 condition所在锁上的其他线程
               condition.signal();
           }finally {
               //释放锁
               lock.unlock();
           }


       }

      }


    /**
     * main start b
       main block
       tb workup other
       main continue
     */
}
