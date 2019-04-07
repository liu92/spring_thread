package com.thread.catchexception;

import com.thread.lock.DefaultThreadFactory;

import java.util.concurrent.*;
  /**
     *  java类简单作用描述
     * @ProjectName:    DemoCatchException.java
     * @Package:        com.thread.catchexception
     * @ClassName:      DemoCatchException
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/23 0023 15:27
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/23 0023 15:27
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoCatchException implements Runnable{

	@Override
	public void run() {
		System.out.println("准备抛出异常");
		throw new RuntimeException();
	}

	
	public static void main(String[] args) {
		try {

			ExecutorService executorService = new ThreadPoolExecutor(5,20,
					200, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),
					new DefaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
			executorService.execute(new DemoCatchException());
			executorService.shutdown();

		} catch (Exception e) {
			System.out.println("我捕捉到异常了。。。。。");
			e.printStackTrace();
		}
	}
}
