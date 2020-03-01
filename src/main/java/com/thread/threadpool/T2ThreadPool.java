package com.thread.threadpool;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @ClassName: T2ThreadPool
 * @Description:
 * @Author: lin
 * @Date: 2019/11/24 23:32
 * History:
 * @<version> 1.0
 */
public class T2ThreadPool {
    public static void main(String[] args) throws InterruptedException {

      ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(200),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
      int c = 6;
      for (int i=0; i<c; i++){
          executorService.execute(()->{
              try {
                  // 每个任务 睡5s钟
                  TimeUnit.MILLISECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName());
          });
      }
        System.out.println("executorService线程池开始详细信息: "+executorService);
        //正常关闭，等所有任务执行完 才关闭
        executorService.shutdown();
        // isTerminated 所有的任务是否执行完
        System.out.println("所有的任务是否执行完：" +executorService.isTerminated());
        // isShutdown是否关闭了 true表示关闭了， 但是不代表任务执行完了
        System.out.println("isShutdown是否关闭了"+ executorService.isShutdown());
        System.out.println("executorService线程池关闭后其线程池详细信息："+ executorService);

        TimeUnit.SECONDS.sleep(3);
        System.out.println(executorService.isTerminated());
        System.out.println(executorService.isShutdown());
        System.out.println("executorService线程池完成详细信息："+ executorService);
        /**
         * 打印信息：线程状态Terminated 表示正常结束， completed tasks 表示完成任务列表
         * java.util.concurrent.ThreadPoolExecutor@79fc0f2f[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 6]
         */
    }
}
