package com.thread.waittimeout;

/**
 * 4对应2
 * 
 * 测试 wait(long timeout) 等待（阻塞）状态
 * <p>Title:WaitTimeOutDemo</p>
 * @author liuwanlin
 * @date 2017年8月21日下15:03:27
 */
public class WaitTimeOutDemo {

	public static void main(String[] args) {
		ThreadTimeoutWaitTimeOutDemo t1=new ThreadTimeoutWaitTimeOutDemo("t1");
		synchronized (t1) {
			try {
				// 启动“线程t1”
				System.out.println(Thread.currentThread().getName()+" start t1");
				t1.start();
				
				// 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
				System.out.println(Thread.currentThread().getName()+" call wait ");
				t1.wait(2000);
				
				System.out.println(Thread.currentThread().getName()+" continue");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//  运行结果
	//	main start t1
	//	main call wait 
	//	t1 run
	//	main continue
	
    //   主线程代表WaitTimeOutDemo主线程（即：线程mian）。线程t1代表WaitTimeOutDemo中启动的t1线程。
	//    而“锁” 代表“t1这个对象的同步锁”
	//   主线程main执行t1.start启动线程t1,
	//   主线程main执行t1.wait(2000),此时线程进入“阻塞状态”
	//   需要“用于t1对象锁的线程通过notify() 或者 notifyAll()将其唤醒” 或者 “超时2000ms之后，主线程main才进入到“就绪状态”，然后才可以运行
	//   "t1线程"运行之后，进入死循环，一直不断地运行。
	//   超时2000ms之后，主线程main会进入到“就绪状态”，然后接着进入“运行状态"
	
}
