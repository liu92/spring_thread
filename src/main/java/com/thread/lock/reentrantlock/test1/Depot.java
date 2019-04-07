package com.thread.lock.reentrantlock.test1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 示例1
 * 
 * 测试 ReentrantLock 是一个可重入的互斥锁，又被称为“独占锁”。
 *  顾名思义，ReentrantLock锁在同一个时间点只能被一个线程锁持有；
 *  而可重入的意思是，ReentrantLock锁，可以被单个线程多次获取。
 * <p>Title:Depot</p>
 * @author liuwanlin
 * @date 2018年2月12日下午5:43:04
 */
public class Depot {
	/**
	 * 数量
 	 */
  private int size;
	/**
	 * 独占锁
	 */
	private Lock lock;
  
   Depot(){
	  this.size=0;
	  this.lock= new ReentrantLock();
  }
  
  
  public void produce(int val){
	  lock.lock();
	  try {
		size+=val;
		System.out.printf("%s produce(%d) --> size=%d\n", 
                Thread.currentThread().getName(), val, size);
	  } finally {
		  lock.unlock();
	  }
  }
  
  public void consume(int val) {
	  lock.lock();
	  try {
		  size -= val;
		  System.out.printf("%s consume(%d) <-- size=%d\n", 
                  Thread.currentThread().getName(), val, size);
	   } finally{
		  lock.unlock();
	   }
  }
  
}
