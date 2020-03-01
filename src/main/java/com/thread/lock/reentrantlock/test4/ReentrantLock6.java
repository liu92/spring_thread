package com.thread.lock.reentrantlock.test4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的使用。
 * 使用ReentrantLock 可以完成synchronized同样的功能
 * 需要注意的是，必须必须必须 要进行手动释放锁（重要的事情说三部）
 * 在使用synchronized 锁定的话如果遇到了异常，jvm会自动释放锁，
 *  但是lock必须手动释放锁，因此经常在finally进行锁的释放。
 *
 * 使用ReentrantLock可以进行“尝试锁定”tryLock, 这样无法锁定，或者在指定时间内无法锁定，
 * 线程可以决定是否继续等待
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法， 可以对线程interrupt方法做出响应
 * 在一个线程中等待锁的释放过程中，可以被打断
 *
 * @ClassName: ReentrantLock4
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 10:11
 * History:
 * @<version> 1.0
 */
public class ReentrantLock6 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                System.out.println("t1 start");
                // 这样我们去睡很多长的时间， 比如说有一个资源 这把锁 锁定了某个资源 ，并且在这里不停的运行。
                // 而另外的线程也想去访问这个资源，可以访问了很长的时间都没有这把锁。这时候该怎么办呢？
                // 我们就不想让线程等待了 我们在怎么办呢？
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("...........interrupted!.......");
            }finally {
                lock.unlock();
            }
        });
        t1.start();


        Thread t2 = new Thread(()->{
            // 这个就是去等待锁，但是一直没有获取到
            //lock.lock();
            try {
                // 可以对Interrupt()方法做出响应
                // 举例说明：比如有一个线程t1它长时间的执行占据某个资源，某个锁，
                // 而另外一个线程t2 他也想申请获得这把锁，但是申请一段时间后 总是申请不到这把锁，
                // 所以它不想继续等待了，这个时候 主线程就可以将把这个线程打断，怎么打断呢？
                // 就是通过lockInterruptibly 告知t2，让其不在等待，直接把自己断掉就行了，大概就是这个意思。。。
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("...........interrupted!.......");
            }finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打断线程2的等待
        t2.interrupt();

//        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
//        ExecutorService executorService = new ThreadPoolExecutor(5,
//                10, 200,
//                TimeUnit.MILLISECONDS, arrayBlockingQueue,
//                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


//        MyTaskReentrantLock3 t1 = new MyTaskReentrantLock3("t1", lock);
//
//        executorService.execute(t1);
//        MyTaskReentrantLock4 t2 = new MyTaskReentrantLock4("t2", lock);
//        Future<?> task = executorService.submit(t2);
//
//        // 发送关闭线程池的指令
//        executorService.shutdown();
//        // 在 2 秒之后取消该任务
//        cancelTask(task, 1000);
    }

//    private static void cancelTask(final Future<?> future, final int delay) {
//        Runnable cancellation = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(delay);
//                    // 取消与 future 关联的正在运行的任务
//                    future.cancel(true);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace(System.err);
//                }
//            }
//        };
//        new Thread(cancellation).start();
//    }
//
//    static class MyTaskReentrantLock3 extends Thread {
//        String name;
//        Lock lock;
//        MyTaskReentrantLock3(String name, Lock lock) {
//            this.name = name;
//            this.lock = lock;
//        }
//        @Override
//        public void run() {
//            lock.lock();
//            try {
//                System.out.println("t1 start" + name);
//                // 这样我们去睡很多长的时间， 比如说有一个资源 这把锁 锁定了某个资源 ，并且在这里不停的运行。
//                // 而另外的线程也想去访问这个资源，可以访问了很长的时间都没有这把锁。这时候该怎么办呢？
//                // 我们就不想让线程等待了 我们在怎么办呢？
//                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
//                System.out.println("t1 end" + name);
//            } catch (InterruptedException e) {
//                System.out.println("t1...........interrupted!");
//            }finally {
//                lock.unlock();
//            }
//        }
//    }
//
//    static class MyTaskReentrantLock4 extends Thread {
//        String name;
//        Lock lock;
//        MyTaskReentrantLock4(String name, Lock lock) {
//            this.name = name;
//            this.lock = lock;
//        }
//        @Override
//        public void run() {
//            boolean flag = false;
//            // t2去申请这把锁 ，但是t1已经把这把锁给占用了，并且没有释放， 所以t2永远也得不到这把锁。那么该怎么办呢？
//            //lock.lock();
//            try {
////                flag = lock.tryLock();
//                // 可以对Interrupt()方法做出响应
//                lock.lockInterruptibly();
//                System.out.println("t2 start");
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                System.out.println("thread------------interrupted!");
//            }finally {
////                if(flag) {
//                    lock.unlock();
////                }
//            }
//        }
//
//    }


}
