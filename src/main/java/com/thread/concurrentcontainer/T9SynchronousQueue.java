package com.thread.concurrentcontainer;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * SynchronousQueue队列
 *  容量为0的队列，也就是生成者生产的任何东西 必须马上消费掉
 * @ClassName: T6ArrayBlockingQueue
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:59
 * History:
 * @<version> 1.0
 */
public class T9SynchronousQueue {
    private  static BlockingQueue<String> str = new SynchronousQueue<>();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        ThreadSynchronousQueue ths = new ThreadSynchronousQueue();
        executorService.execute(ths);
        //阻塞等待消费者消费
         str.put("add");
         // 调用报错
         // str.add("aaa");
         System.out.println(str.size());
    }

    static  class  ThreadSynchronousQueue implements Runnable{
        @Override
        public void run() {
            try {
                str.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }







}
