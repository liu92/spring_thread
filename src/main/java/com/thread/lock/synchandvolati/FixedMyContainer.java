package com.thread.lock.synchandvolati;

import com.thread.lock.DefaultThreadFactory;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 面试题，写一个固定的容量的同部容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 题意解读，同步容器 就是说在put的时候 如果容器中满了，我们就不能往容器中添加数据了，线程就得等着
 * 调用get方法时 如果容器空了没有数据那么 调用get方法的线程就得等着。
 * @ClassName: FixedMyContainer
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 13:34
 * History:
 * @<version> 1.0
 */
public class FixedMyContainer<T>{
    final  private LinkedList<T> lists = new LinkedList<>();
    /**
     * 最少10个元素
     */
    final  private  int MAX = 10;
    private int count = 0;

   public synchronized  void  put(T t){
       // 这里为是什么不用if?
       // 如果这里用if, 如果这个时候容器中元素满了的时候，线程t1 就在这里进行wait,
       // 如果消费者从容器中拿走 一个元素，这个时候t1线程被唤醒后，就会往下执行，
       // 当执行到lists.add(t); 时 刚要往容器中添加元素时， 其他的线程t2 已经往容器中添加了一个元素，
       // 这个时候就出现了问题 超过了容器的容量。 因为wait是会释放锁的。所以会有其他线程执行到这里 ，
       // 然后在t1线程还没有往容器中添加数据时，其它线程t2已经往容器中添加了数据。
       // 这时t1再向容器中添加元素时，已经超过了容器的容量 所以就出错了
       // 如果使用while呢？ wait之后继续往下执行，会再一次的检查一遍，去判断这个容器数量是否已满，这样就不会出现问题了
       // 不给其他的线程机会，你想notifyAll是会叫醒两个线程。所以再次判断就是不让其他线程来进行处理。。。
       // if只会判断一次，while会 一直判断
       while (lists.size() == MAX){
           try {
               //在 effective java 说wait 一般和while结合使用，而不是if
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       lists.add(t);
       ++count;
       // 通知消费者线程进行消费
       // 如果这里使用notify 因为只叫醒一个线程
       //  那么你叫醒的又有可能是生产者 ，叫醒的生产者 一进来就去检查，当发现这个容器满了，那么这个生产者就wait了。
       //  这个时候就只有一个线程醒着就是这个生产者，而这个生产者wait那么程序 就执行不动了
       //  所以这里调用notifyAll() 就将消费者全部唤醒。
       this.notifyAll();
   }

  public  synchronized  T get(){
       T t = null;
       while (lists.size() == 0){
           try {
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       t = lists.removeFirst();
       count --;
       // 通知生产者进行生产
       this.notifyAll();
       return  t;
  }

    public static void main(String[] args) {
        FixedMyContainer<String> fixedMyContainer = new FixedMyContainer<>();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        //启动消费者线程
        int count =10;
        for (int i=0; i<count; i++){
            MyTaskFixed1 fixed1 = new MyTaskFixed1("fixedMyContainer" + i, fixedMyContainer);
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
        FixedMyContainer<String> fixedMyContainer;
        MyTaskFixed1(String name, FixedMyContainer<String> fixedMyContainer) {
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
        FixedMyContainer<String> fixedMyContainer;
        MyTaskFixed2(String name, FixedMyContainer<String> fixedMyContainer) {
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
