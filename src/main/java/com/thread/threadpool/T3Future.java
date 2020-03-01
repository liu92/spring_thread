package com.thread.threadpool;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * Future 表示未来的结果，有返回值
 * @ClassName: T3Future
 * @Description:
 * @Author: lin
 * @Date: 2019/11/24 23:41
 * History:
 * @<version> 1.0
 */
public class T3Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(()->{
            // 500毫秒
            TimeUnit.MILLISECONDS.sleep(500);
            return  1000;
        });

        new  Thread(task).start();

        // 阻塞，task.get() 这里拿到就是FutureTask返回的值。
        // 阻塞的意思就是一直等任务执行完，什么时候执行完了 ，什么时候才拿到这个值
        System.out.println(task.get());

        //线程池起来之后就跑在那里，
        // ExecutorService是服务 就是不停的运行 然后等着你往里面放任务
        // 不放任务 它就在那里一直运行着，什么时候想让他结束那么就调用 executorService.shutdown(); 方法等
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10, 200,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(200),
                new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<Integer> f = executorService.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(f.get());
        System.out.println("任务执行完没有："+f.isDone());

    }
}
