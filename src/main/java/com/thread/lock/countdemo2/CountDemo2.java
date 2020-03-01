package com.thread.lock.countdemo2;

/**
 * 第二条：当一个线程访问“某对象”的“synchronized方法”
 * 或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
 * <p>
 * Title:Count
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月15日下午2:42:18
 * @see Demo2
 */
public class CountDemo2 {
	/**
	 *  含有synchronized同步块的方法
	 */
	public void SynMethod() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
					System.out.println("同步块 CountDemo2当前线程="+Thread.currentThread().getName()
							+ " synMethod loop " + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}


	/**
	 * 这个方法也就等同于 ，在方法的执行时 要 synchronized (this), 所以 执行代码的时候不是锁定整段代码，而是锁定当前对象。
	 */
	public synchronized  void  tm(){
		System.out.println("-----tm-----");
	}

	/**
	 * 这里等同于 synchronized (CountDemo2.class)
	 */
	public static synchronized  void  test(){
		System.out.println("----test------");
	}

	/**
	 * 这里等同于 synchronized (CountDemo2.class)
	 */
	public static void  dem(){
		// 这里能写成 synchronized (this) 是否可以？ 因为静态的属性和方法你是不需要去new 出对象的。
		// 那么这个对象不new出来 那么就没有this的存在。
		synchronized (CountDemo2.class){
			System.out.println("-----------------");
		}
	}

	/**
	 * 非同步的方法
	 */
	public void noSynMethod() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(100);
				System.out.println("非同步 CountDemo2当前线程="+Thread.currentThread().getName()
						+ " noSynMethod loop " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
