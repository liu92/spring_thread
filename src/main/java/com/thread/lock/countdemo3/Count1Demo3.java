package com.thread.lock.countdemo3;

/**
 * 第一条： 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 * 其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
 * 我们将上面的例子中的nonSynMethod()方法体的也用synchronized(this)修饰。
 * <p>
 * Title:Count_1
 * </p>
 * 
 * @author liuwanlin
 * @date 2017年8月16日上午10:38:11
 * @see Demo3
 */
public class Count1Demo3 {
	/**
	 * 含有synchronized同步块的方法
	 */
	public void SynMethod() {

		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
					System.out.println("Count1Demo3当前线程="+Thread.currentThread().getName()
							+ " synMethod loop " + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * 也包含synchronized同步块的方法
	 */
	public void noSynMethod() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
					System.out.println("Count1Demo3当前线程="+Thread.currentThread().getName()
							+ " noSynMethod loop " + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
