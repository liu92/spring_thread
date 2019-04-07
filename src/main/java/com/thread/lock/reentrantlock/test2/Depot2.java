package com.thread.lock.reentrantlock.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock 是一个可重入的互斥锁，又被称为“独占锁”。
 *  顾名思义，ReentrantLock 锁在同一个时间点只能被一个线程锁持有；
 *  而可重入的意思是，ReentrantLock锁，可以被单个线程多次获取。
 * <p>Title:Depot2</p>
 * @author liuwanlin
 * @date 2018年2月12日下午6:25:59
 */
public class Depot2 {
	/**
	 * // 仓库的实际数量
	 */
	 private int size;
	/**
	 * //独占锁
	 */
	private Lock lock;
	 

	 public Depot2() {
	   this.size = 0;
	   this.lock = new ReentrantLock();
	 }
	 
	 public void produce(int val) {
//       lock.lock();
//       try {
           size += val;
           System.out.printf("%s produce(%d) --> size=%d\n",
                   Thread.currentThread().getName(), val, size);
//       } catch (InterruptedException e) {
//       } finally {
//           lock.unlock();
//       }
   }

	   public void consume(int val) {
//	        lock.lock();
//	        try {
	            size -= val;
	            System.out.printf("%s consume(%d) <-- size=%d\n",
	                    Thread.currentThread().getName(), val, size);
//	        } finally {
//	            lock.unlock();
//	        }
	    }


}
