package com.thread.lock.demo1;

/**
 *  synchronized 关键字
 * <p>Title:Myrunable</p>
 * @author liuwanlin
 * @date 2017年8月15日下午12:26:01
 */
public class Myrunable implements Runnable {


	/** 第一条:
	// 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
	// 其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
	
	// 第二条:
	// 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。

	 // 第三条:
	// 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
	// 其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。

	 // 第四条
	  一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍可以得到该对象的锁
	   也就是说synchronized获得的锁是可以重入的。
	*/



	/**
	 * 测试第一条
	 * @Title:run
	 * @Description:
	 * @authwor liuwanlin
	 * @date 2017年8月15日下午1:04:20
	 */
	@Override
	public void run() {
	// 当前类的对像
      synchronized (this) {
		 try {
			for (int i = 0; i < 5; i++) {
				// 休眠100ms
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName()+" loop"+i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	 }
	}


//	public Myrunable(String name){
//		this.name = name;
//	}


}
