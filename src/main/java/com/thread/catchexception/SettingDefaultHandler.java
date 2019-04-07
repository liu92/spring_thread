package com.thread.catchexception;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;

  /**
     *  java类简单作用描述
     * @ProjectName:    SettingDefaultHandler.java
     * @Package:        com.thread.catchexception
     * @ClassName:      SettingDefaultHandler
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/23 0023 15:30
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/23 0023 15:30
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class SettingDefaultHandler {
	public static void main(String[] args) {
		//设置静态异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executorService=  new ThreadPoolExecutor(5,20,
                200, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),
                new DefaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new ExceptionThread2());
        executorService.shutdown();
	}
}
