package com.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
*  自定义线程工厂
* @ClassName:      CustomizeThreadFactory
* @Description:    java类作用描述
* @Author:         作者姓名 liuwl
* @CreateDate:     2018/6/27 0027 16:41
* @Version:        1.0
*/
public class CustomizeThreadFactory implements ThreadFactory {
  private static  final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
  private final ThreadGroup group;
  private final AtomicInteger threadNumber = new AtomicInteger(1);
  private final String namePrefix;

  CustomizeThreadFactory(){
      SecurityManager s = System.getSecurityManager();
      group =(s!=null)?s.getThreadGroup():Thread.currentThread().getThreadGroup();
      //设置 线程 名称 方便问题排查
      namePrefix ="customize-pool-"+POOL_NUMBER.getAndIncrement()+"-thread";
  }


    @Override
    public Thread newThread(Runnable r) {
        // 可定制化处理
        Thread t = new Thread(group,r,namePrefix+threadNumber.getAndIncrement(),0);
        if(t.isDaemon()){
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}