package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法 add, size.
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当达到5个时。线程2给出提示并结束。
 *
 * 下面的这种方式在给lists添加 volatile之后，t2能接受到通知，但是 ，t2线程的死循环很浪费cpu,如果不使用死循环，该怎么做呢？
 * @ClassName: MyContainer
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 22:05
 * History:
 * @<version> 1.0
 */
public class MyContainer {
    /**
     * 如果这里不加volatile， 那么t2就不会去执行 然后停止，因为t1这个线程的操作对t2不可见。
     * 所以t2线程不知道，因此加上volatile 让t2能得到通知
     */
   volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
       return lists.size();
    }

    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new MyTask1("t1",container));
        executorService.execute(new MyTask2("t2",container));
        executorService.shutdown();
    }



    static class MyTask1 implements Runnable {
        String name;
        MyContainer container;
        MyTask1(String name, MyContainer container) {
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            int count =10;
            for (int i = 0; i< count ; i++){
                container.add(new Object());
                System.out.println(Thread.currentThread().getName() +" add==" +i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyTask2 implements Runnable {
        String name;
        MyContainer container;
        MyTask2(String name, MyContainer container) {
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            while (true){
                // 这种方式不精确
                if(container.size() == 5){
                  break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " t2结束");
        }
    }
}
