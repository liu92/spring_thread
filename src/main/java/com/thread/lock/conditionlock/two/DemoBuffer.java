package com.thread.lock.conditionlock.two;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   *
   * Condition  能够支持多个等待队列（new 多个Condition对象）
   * @ProjectName:    DemoBuffer.java
   * @Package:        com.thread.lock.conditionlock.two
   * @ClassName:      DemoBuffer
   * @Description:    java类作用描述
   * @Author:         作者姓名
   * @CreateDate:     2018/11/30 0030 12:08
   * @UpdateUser:     作者姓名
   * @UpdateDate:     2018/11/30 0030 12:08
   * @UpdateRemark:   The modified content
   * @Version:        1.0
   */

public class DemoBuffer {

    final Lock lock = new ReentrantLock();
    /**
     * 写线程条件
     *
     */
    final Condition notFull = lock.newCondition();
    /**
     * 读线程条件
     */
    final Condition notEmpty = lock.newCondition();

    final  Object[] items = new Object[5];

    /**
     * 读索引、写索引、 队列中存在的数据个数
     *
     */
    int putptr, takeptr, count;


    public void put(Object x){
       lock.lock();


           try {
               // 如果“缓冲已满”，则等待；直到“缓冲”不是满的，才将x添加到缓冲中。
               while (count == items.length){

                   notFull.await();
               }
               // 将x添加到缓冲中
               items[putptr] = x;
               // 将“put统计数putCache+1”；如果“缓冲已满”，则设putCache为0。

               if(++putptr == items.length){
                   putptr = 0;
               }
               // 将“缓冲”数量+1
               count++;
               // 唤醒take线程，因为take线程通过notEmpty.await()等待
               notEmpty.signal();

               System.out.println(Thread.currentThread().getName()+" put " + x);

           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           finally {
               lock.unlock();
           }

    }


    public  void  take(){
        lock.lock();
        // 如果“缓冲为空”，则等待；直到“缓冲”不为空，才将x从缓冲中取出。

        try {
            while (count == 0){
                notEmpty.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
