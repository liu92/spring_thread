package com.thread.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
  /**
   * 自定义RejectedExecutionHandler。记录异常信息，选择不同处理逻辑，
   * 有交由当前线程执行任务，有直接抛出异常，再或者等待后继续添加任务等。
   * @Description:    java类作用描述
   * @Author:         作者姓名
   * @CreateDate:     2018/6/27 0027 17:50
   */

public class CustomizeRejectedExecutionHandler implements RejectedExecutionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeRejectedExecutionHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

         LOGGER.error("向线程池添加任务被拒绝...");
         r.run();

         // 自定义 处理逻辑
         throw  new RejectedExecutionException("Task"+r.toString()+" rejected from " +executor.toString());
    }
}
