package com.thread.startAndrun;

/**
 * 测试 start 和 run的 区别
 * <p>Title:DemoStart</p>
 * @author liuwanlin
 * @date 2017年12月29日下午5:15:58
 */
public class DemoStart {
	public static void main(String[] args) {
      Thread startDemo = new StartDemo("startDemo");
      System.out.println(Thread.currentThread().getName()+" call startDemo.run");
      startDemo.run();
      System.out.println(Thread.currentThread().getName()+" call startDemo.start");
      startDemo.start();
      
      Thread thread = new Thread(new DemoRunnable());
      thread.run();
      thread.start();
      thread.start();
     // 从结果来看  startDemo.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
     // main call startDemo.run
     // main is running  
      
      
     // startDemo.start()会启动“线程startDemo”，“线程startDemo”启动之后，
     // 会调用run()方法；此时的run()方法是运行在“线程startDemo”上
     // main call startDemo.start
     // startDemo is running


      // 从jdk 源码可以知道
      // start() 方法实际上是通过本地方法start0()启动线程的 ，
      // 而start0()会新运行一个线程，新线程会调用run()方法。
      // run() 是直接 运行

	}
}
