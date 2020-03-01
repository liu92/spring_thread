package com.thread.lock.synchandvolati;

import com.thread.lock.DefaultThreadFactory;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题，写一个固定的容量的同部容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 *
 *
 * 题意解读，同步容器 就是说在put的时候 如果容器中满了，我们就不能往容器中添加数据了，线程就得等着
 * 调用get方法时 如果容器空了没有数据那么 调用get方法的线程就得等着。
 *
 * 可以使用Lock和Condition来实现
 * 对比这两种方式，Condition的方式可以更加精确的指定那些线程被唤醒
 * @ClassName: FixedMyContainer
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 13:34
 * History:
 * @<version> 1.0
 */
public class FixedMyContainer1<T>{
    final  private LinkedList<T> lists = new LinkedList<>();
    /**
     * 最少10个元素
     */
    final  private  int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    /**
     * new两个条件 出来，
     * 在条件producer下做什么, 生产者的条件
     * 在条件consumer下做什么，消费者的条件
     */
    private Condition producer = lock.newCondition();
    /**
     * 使用lock , condition 可以精确的通知那些线程被叫醒，那些线程不必被叫醒。
     * 具体到那一个线程？ 就是一个newCondition中只有一个线程在这个 Condition上进行等待，
     * 然后叫醒的时候 也是用的这个Condition 叫醒 这样就叫醒了一个线程了。
     */
    private Condition consumer = lock.newCondition();

   public   void  put(T t){
      try {
          lock.lock();
          while (lists.size() == MAX){
              //生产者在这种条件下 全部等着
              producer.await();
          }
          lists.add(t);
          ++count;
          // 通知消费者线程进行消费
          // 这里叫醒所以消费者
          consumer.signalAll();
      }catch (InterruptedException e){
          e.printStackTrace();
      }finally {
          lock.unlock();
      }
   }

    /**
     *
     * @return
     */
   public  T get(){
       T t = null;
      try {
          lock.lock();
          while (lists.size() == 0){
              // 如果容器中空了的话，消费者全部等着
             consumer.await();
          }
          t = lists.removeFirst();
          count --;
          // 通知生产者进行生产
          // 然后叫醒 所以生产者
          // 使用lock , condition 可以精确的通知那些线程被叫醒，那些线程不必被叫醒。
          producer.signalAll();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }finally {
          lock.unlock();
      }

       return  t;
  }

    public static void main(String[] args) {
        FixedMyContainer1<String> fixedMyContainer = new FixedMyContainer1<>();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        //启动消费者线程
        int count =10;
        for (int i=0; i<count; i++){
            MyTaskFixed1 fixed1 = new MyTaskFixed1("c" + i, fixedMyContainer);
            executorService.execute(fixed1);
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int cou =2 ;
        for (int i=0; i<cou; i++){
            MyTaskFixed2 fixed1 = new MyTaskFixed2("fixedMyContainer" + i, fixedMyContainer);
            executorService.execute(fixed1);
        }
    }






    static class MyTaskFixed1 implements Runnable {
        String name;
        FixedMyContainer1<String> fixedMyContainer;
        MyTaskFixed1(String name, FixedMyContainer1<String> fixedMyContainer) {
            this.name = name;
            this.fixedMyContainer = fixedMyContainer;
        }
        @Override
        public void run() {
            int t =5;
            for (int j =0; j<t; j++){
                System.out.println(fixedMyContainer.get());
            }
        }
    }

    static class MyTaskFixed2 implements Runnable {
        String name;
        FixedMyContainer1<String> fixedMyContainer;
        MyTaskFixed2(String name, FixedMyContainer1<String> fixedMyContainer) {
            this.name = name;
            this.fixedMyContainer = fixedMyContainer;
        }
        @Override
        public void run() {
            int t = 25;
            for (int j =0; j<t; j++){
                fixedMyContainer.put(Thread.currentThread().getName()+" "+j);
            }
        }
    }



}
