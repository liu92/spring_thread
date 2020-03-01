package com.thread.sleep;

/**
 * 07:
 * wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，也会释放同步锁。
 * 而sleep()的作用是也是让当前线程由“运行状态”进入到“休眠(阻塞)状态”。
 * 但是，wait()会释放对象的同步锁，而sleep()则不会释放锁。
 * <p>Title:SleepLockTest</p>
 * @author liuwanlin
 * @date 2018年1月2日下午3:27:03
 */
public class SleepLockTest {
	/**
	 * 属于类对象的
	 */
	private static Object obj = new Object();
	
	public static void main(String[] args){
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
				try {
					 for(int i=0; i <10; i++){ 
	                        System.out.printf("%s: %d\n", this.getName(), i); 
	                        // i能被4整除时，休眠100毫秒
	                        if (i%4 == 0) {
								System.out.println("当前线程的开始状态-------------"+Thread.currentThread().getState());
								Thread.sleep(100);
								System.out.println("当前线程执行sleep后的状态-------------"+Thread.currentThread().getState());
							}
	                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 *  主线程main中启动了两个线程t1和t2。t1和t2在run()会引用同一个对象的同步锁，即synchronized(obj)。
	 *  在t1运行过程中，虽然它会调用Thread.sleep(100)；但是，t2是不会获取cpu执行权的。因为，t1并没有释放“obj所持有的同步锁”！
	 */
	
	
	//	t1: 0
	//	t1: 1
	//	t1: 2
	//	t1: 3
	//	t1: 4
	//	t1: 5
	//	t1: 6
	//	t1: 7
	//	t1: 8
	//	t1: 9
	//	t2: 0
	//	t2: 1
	//	t2: 2
	//	t2: 3
	//	t2: 4
	//	t2: 5
	//	t2: 6
	//	t2: 7
	//	t2: 8
	//	t2: 9
}
