package com.thread.yield;

/**
 * 06:
 * yield() 和 wait() 的作用比较。
 * 
 * 我们知道，wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，
 * 也会释放同步锁。而yield()的作用是让步，它也会让当前线程离开“运行状态”。
 * 
 * 它们的区别是：
 * (01) wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而yield()是让线程由“运行状态”进入到“就绪状态”。
 * (02) wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。
 * 
 * <p>Title:YieldLockTest</p>
 * @author liuwanlin
 * @date 2018年1月2日上午11:24:21
 */
public class YieldLockTest {

	private static Object obj=new Object();
	
	public static void main(String[] args) {
		ThreadA t1=new ThreadA("t1");
		ThreadA t2=new ThreadA("t2");
	    t1.start();
	    t2.start();
	}
	
	static class ThreadA extends Thread{
		
		public ThreadA(String name){
			super(name);
		}
		
		@Override
		public void run(){
           synchronized (obj) {
			   for (int i = 0; i < 10; i++) {
				System.out.printf("%s [%d]:%d\n",this.getName(),this.getPriority(),i);
				// i整除4时，调用yield
				if(i%4==0){
					Thread.yield();
				}
			 }
		   }			
		}
	}
		
	//	主线程main中启动了两个线程t1和t2。t1和t2在run()会引用同一个对象的同步锁，即synchronized(obj)。
	//	在t1运行过程中，虽然它会调用Thread.yield()；但是，t2是不会获取cpu执行权的。因为，t1并没有释放“obj所持有的同步锁”！
	
	//	t1 [5]:0
	//	t1 [5]:1
	//	t1 [5]:2
	//	t1 [5]:3
	//	t1 [5]:4
	//	t1 [5]:5
	//	t1 [5]:6
	//	t1 [5]:7
	//	t1 [5]:8
	//	t1 [5]:9
	//	t2 [5]:0
	//	t2 [5]:1
	//	t2 [5]:2
	//	t2 [5]:3
	//	t2 [5]:4
	//	t2 [5]:5
	//	t2 [5]:6
	//	t2 [5]:7
	//	t2 [5]:8
	//	t2 [5]:9
	
}
