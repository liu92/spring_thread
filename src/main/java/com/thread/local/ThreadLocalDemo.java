/**
 * Copyright (C), 2018-2019, XXX有限公司
 * FileName: ThreadLocalDemo
 * Author:   lin
 * Date:     2019/1/14 20:34
 * Description: 测试ThreadLocal的使用方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thread.local;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试ThreadLocal的使用方法〉
 *
 * @author lin
 * @create 2019/1/14
 * @since 1.0.0
 */
public class ThreadLocalDemo {


    private static  final AtomicInteger NEXT_ID = new AtomicInteger(0);

    private static  final ThreadLocal<Integer> THREAD_ID = ThreadLocal.withInitial(NEXT_ID::getAndIncrement);

    public static  int get(){
        return  THREAD_ID.get();
    }



    public static  void  main(String [] args){
        ExecutorService executorService = new ThreadPoolExecutor(5,
                50, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        ThreadMyRunable threadMyRunable = new ThreadMyRunable();
        executorService.execute(threadMyRunable);
        executorService.shutdown();

    }

    static class ThreadMyRunable implements Runnable{


        @Override
        public void run() {
            int count = 5;
            for (int i = 0; i<count;i++){
                System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + THREAD_ID.get());
            }
        }
    }

}
