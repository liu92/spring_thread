/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MyAppThread
 * Author:   lin
 * Date:     2018/12/25 23:09
 * Description: 在线程转储和错误日志信息中区分来自不同线程池的线程
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thread.atomic.demoreference;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 〈一句话功能简述〉<br> 
 * 〈在线程转储和错误日志信息中区分来自不同线程池的线程〉
 *
 * @author lin
 * @create 2018/12/25
 * @since 1.0.0
 */
public class MyAppThread extends  Thread{
    public static final String DEFAULT_NAME="MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();
    public MyAppThread(Runnable r){
           this(r,DEFAULT_NAME);
    }

    public MyAppThread(Runnable r ,String name){
        super(r,name+"-"+created.incrementAndGet());
        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void  uncaughtException(Thread t, Throwable e){
               log.log(Level.SEVERE,"UNCAUGHT in thread "+ t.getName(),e);
            }
        });
    }



    @Override
    public  void  run(){
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created" + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if(debug){
                log.log(Level.FINE,"Exiting "+ getName());
            }
        }

    }
}
