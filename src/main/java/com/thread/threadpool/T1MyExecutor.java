package com.thread.threadpool;

import java.util.concurrent.Executor;

/**
 * MyExecutor
 * @ClassName: T1MyExecutor
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 23:49
 * History:
 * @<version> 1.0
 */
public class T1MyExecutor implements Executor {
    public static void main(String[] args) {
        new T1MyExecutor().execute(()-> System.out.println("hello executor"));
    }
    /**
     */
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
