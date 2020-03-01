package com.thread.lock.countdemo2;

import com.thread.lock.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 在上面的两个 示例中使用的wait/notify 方式来处理。
 *  notify过后 t1需要进行锁的释放,t2退出后， 也必须notify,通知t1继续执行，整个通信过程比较繁琐
 *
 *  使用Latch 代替wait ,notify来进行通信
 *  好处就是通信简单，同时也可以指定等待时间
 *  使用await和countdown方法代替 wait和notify
 *  CountDownLatch不涉及锁定，当count的值为零是当前线程继续进行，
 *  当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify方式就显得太重了
 *  这时应该考虑 CountDownLatch/CyclicBarrier/Semaphore
 * @ClassName: MyContainer2
 * @Description:
 * @Author: lin
 * @Date: 2019/11/22 23:25
 * History:
 * @<version> 1.0
 */
public class MyContainer2 {
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }
    public int size(){
        return lists.size();
    }
    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();
        //当这个1变成0时，这个门闩就开了
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        //这个在前面的意思就是 ，元素个数不等于5 那么就wait，知道下面的添加元素进去之后，当其元素等于5时候，就将其唤醒。
        executorService.execute(new MyTask4(countDownLatch, "t2", container));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 一秒钟之后往里添加数据
        executorService.execute(new MyTask3(countDownLatch,"t1", container));
        executorService.shutdown();
    }



    static class MyTask3 implements Runnable {
        CountDownLatch countDownLatch;
        String name;
        MyContainer2 container;
        MyTask3(CountDownLatch countDownLatch, String name, MyContainer2 container) {
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            int count =10;
            System.out.println("t1启动");
                for (int i = 0; i< count ; i++){
                    container.add(new Object());
                    System.out.println(name +" add==" +i);
                    if(container.size() == 5){
                        // 打开门闩，让t2得以执行，只要这个countDown调用一次，这个数就往下面减。
                        //  这里的数是1所以，减1次这个门闩就开了
                        countDownLatch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }
    }

    static class MyTask4 implements Runnable {
        CountDownLatch countDownLatch;
        String name;
        MyContainer2 container;
        MyTask4(CountDownLatch countDownLatch, String name, MyContainer2 container) {
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.container = container;
        }
        @Override
        public void run() {
            int co = 5;
                System.out.println("t2启动");
                // 这里的意思就是，在加锁的情况下，如果这个容器中元素没有等于5，也就是不满足条件的情况下。
                // 我们就线程暂停等着，然后在这个对象的wait方法，那么当前线程进入等待状态 同时释放锁, 这时其他的线程可以进来。
                // 那么什么时候这个线程会再次启动呢？只有在调用这个对象的notify方法。
                // notify方法会启动一个正在这个对象上等待的某一线程
                if(container.size() != co){
                    try {
                        // 这里门闩等待，也就是等着开门，和wait作用一样，但是门闩等待是不需要锁定任何对象的。
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");

            }

    }
}
