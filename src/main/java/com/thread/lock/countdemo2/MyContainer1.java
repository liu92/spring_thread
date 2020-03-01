package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用wait和 notify ， wait释放锁，notify不释放锁。
 * 下面的这种方式不是在size=5时 t2退出 而是t1结束时候 t2才退出 为什么呢？  因为notify不释放锁。
 *  即便你通知了t2 让其重新运行，但是要继续往下运行 ，wait了之后需要获取到这个对象的锁 ，很不幸的是t1已经将其锁定了。
 *  那么怎么办呢？ 我们再在 notify之后再使用wait, 也就是当size=5时，先notify,然后自己去将锁释放掉就可以了
 * @ClassName: MyContainer1
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 22:25
 * History:
 * @<version> 1.0
 */
public class MyContainer1 {
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }
    public int size(){
        return lists.size();
    }
    public static void main(String[] args) {
        MyContainer1 container = new MyContainer1();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        final Object object = new Object();
        //这个在前面的意思就是 ，元素个数不等于5 那么就wait，知道下面的添加元素进去之后，当其元素等于5时候，就将其唤醒。
        executorService.execute(new MyTask4(object, "t2", container));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 一秒钟之后往里添加数据
        executorService.execute(new MyTask3(object,"t1", container));
        executorService.shutdown();
    }



    static class MyTask3 implements Runnable {
        final Object object;
        String name;
        MyContainer1 container;
        MyTask3(Object object, String name, MyContainer1 container) {
            this.object = object;
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            int count =10;
            System.out.println("t1启动");
            synchronized (object){
                for (int i = 0; i< count ; i++){
                    container.add(new Object());
                    System.out.println(name +" add==" +i);
                    if(container.size() == 5){
                        object.notify();
                        // 当size=5时，先notify,然后自己去将锁释放掉就可以了。并且 t2 在收到这个提醒之后，
                        // 通知t1让其继续执行，也就在后面加上 notify(),让其t1继续运行
                        try {
                            // 这里wait了之后，谁来叫醒它呢？就是在t1的线程结束前，在进行notify 进行唤醒操作。
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static class MyTask4 implements Runnable {
        final Object object;
        String name;
        MyContainer1 container;
        MyTask4(Object object, String name, MyContainer1 container) {
            this.object = object;
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            int co = 5;
            synchronized (object){
                System.out.println("t2启动");
                // 这里的意思就是，在加锁的情况下，如果这个容器中元素没有等于5，也就是不满足条件的情况下。
                // 我们就线程暂停等着，然后在这个对象的wait方法，那么当前线程进入等待状态 同时释放锁, 这时其他的线程可以进来。
                // 那么什么时候这个线程会再次启动呢？只有在调用这个对象的notify方法。
                // notify方法会启动一个正在这个对象上等待的某一线程
                if(container.size() != co){
                    try {
                        object.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                // 通知t1继续执行。在线程结束之后锁释放， t2就收到了notify唤醒操作，那么在继续往下执行。
                object.notify();
            }

        }
    }
}
