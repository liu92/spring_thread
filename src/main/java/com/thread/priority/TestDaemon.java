package com.thread.priority;

/**
 * 测试 守护线程
 * <p>Title:TestDaemon</p>
 * @author liuwanlin
 * @date 2018年1月5日下午6:11:06
 */
public class TestDaemon {

	public static void main(String[] args) {
		 System.out.println(Thread.currentThread().getName()
	                +"(isDaemon="+Thread.currentThread().isDaemon()+ ")");
		 
		 Thread t1 =new ThreadDaemon("t1");
		 Thread t2 =new MyDaemon("t2");
		 
		 t2.setDaemon(true);//设置t2为守护线程
		 
		 t1.start();
		 t2.start();
		 
		 /**
		  * 从结果可知，main是主线程。它创建的子线程t1也就是用户线程。
		  * t2是守护线程。在“主线程main”和“子线程t1”(它们都是用户线程)执行完毕后，只剩t2这个守护线程时。jvm会自动退出
		  */
		 
//		 main(isDaemon=false)
//		 t2(isDaemon=true), loop 0
//		 t2(isDaemon=true), loop 1
//		 t2(isDaemon=true), loop 2
//		 t1((是否为守护线程)isDaemon=false), loop 0
//		 t2(isDaemon=true), loop 3
//		 t2(isDaemon=true), loop 4
//		 t2(isDaemon=true), loop 5
//		 t1((是否为守护线程)isDaemon=false), loop 1
//		 t2(isDaemon=true), loop 6
//		 t2(isDaemon=true), loop 7
//		 t2(isDaemon=true), loop 8
//		 t1((是否为守护线程)isDaemon=false), loop 2
//		 t2(isDaemon=true), loop 9
//		 t2(isDaemon=true), loop 10
//		 t2(isDaemon=true), loop 11
//		 t1((是否为守护线程)isDaemon=false), loop 3
//		 t2(isDaemon=true), loop 12
//		 t2(isDaemon=true), loop 13
//		 t1((是否为守护线程)isDaemon=false), loop 4
//		 t2(isDaemon=true), loop 14

	}
}
