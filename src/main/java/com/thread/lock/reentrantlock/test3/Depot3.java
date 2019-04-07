package com.thread.lock.reentrantlock.test3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 示例3中，我们通过 condition 去解决“示例1”中的两个问题，"仓库的容量不可能为负数" 以及"仓库的容量是有限制的"
 * 解决该问题是通过 Condition。Condition是需要和Lock联合使用的：通过Condition中的await()方法，
 * 能让线程阻塞[类似于wait()]；通过Condition的signal()方法，能让唤醒线程[类似于notify()]
 * <p>Title:Depot3</p>
 * @author liuwanlin
 * @date 2018年2月23日上午11:01:12
 */
public class Depot3 {
	/**
	 * // 仓库的容量
	 */
   private int capacity;
	/**
	 * // 仓库的实际数量
	 */
	private int size ;
	/**
	 * // 独占锁
	 */
   private Lock lock;
	/**
	 * // 生产条件
	 */
	private Condition fullCondition;
	/**
	 * // 消费条件
	 */
   private Condition emptyCondition;
   public static ReentrantLock lock1 = new ReentrantLock();
   
    Depot3(int capacity){
	   this.capacity= capacity;
	   this.size=0;
	   this.lock= new ReentrantLock();
	   this.fullCondition=lock.newCondition();
	   this.emptyCondition=lock.newCondition();
   }
   
   /**用来获取锁。如果锁已被其他线程获取，则进行等待。
   //void lock();
   
   // 当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态
	 如果当前线程未被中断，则获取锁。
   //void lockInterruptibly() throws InterruptedException;
   
   //它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
   // boolean tryLock();
   
   //与tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，
   //就返回false。如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
   // boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
   
   //释放锁
   //void unlock();
	*/
   
   public void produce(int val){
	   lock.lock();
	   try {


		  // left 表示“想要生产的数量”(有可能生产量太多，需多此生产)
		  // size=0 ,capacity=100;
		   int left=val;
		   while (left>0) {
			// 库存已满时，等待“消费者”消费产品。
			  while (size >= capacity) {
				  fullCondition.await();//使线程阻塞
			  }
				// 获取“实际生产的数量”(即库存中新增的数量)
                // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                // 否则“实际增量”=“想要生产的数量”
				/**
				 * 实际生成数量
				 */
			    //第一次进来后 是初始化 值 所以size最开始 是0，在进入到这里是 inc = (0+60) ? 100(100-0) : 60 ,so inc=60。
			    //第一个线程 进来后  赋值后size=60
			    //第二个线程 进来后  size还是=60 ，left=120。所以实际容量是100-60=40 ，再进行size+=inc=60+40, left-=inc=120-40
				int inc = (size+left) > capacity ? (capacity-size) : left;
				
				size+=inc;
				left-=inc;
				System.out.printf("%s produce(%3d) --> 想要生成的数量:left=%3d, 实际数量增加:inc=%3d, 仓库的实际数量:size=%3d\n",
	                        Thread.currentThread().getName(), val, left, inc, size);
	                // 通知“消费者”可以消费了。
	            emptyCondition.signal();
			 
		  }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
   }
    
   public void consume(int val){
	   lock.lock();
	   try {
		   // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
		   int left=val;
		   while (left>0) {
			   // 库存为0时，等待“生产者”生产产品。
			   while (size<=0) {
				   emptyCondition.await();
			   }
				// 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
			    // 当 消费线程90 进入时候，size=100 , left=90, size-=dec=100-90=10, left-=dec=90-90=0
				int dec = (size<left)?size:left;
				size-=dec;
				left-=dec;
				System.out.printf("%s consume(%3d) <-- 客户要消费数量:left=%3d, 实际消费的数量:dec=%3d, 仓库剩余的实际数量:size=%3d\n",
	                        Thread.currentThread().getName(), val, left, dec, size);
				fullCondition.signal();
			 
		  }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
   }



   @Override
   public String toString() {
       return "capacity:"+capacity+", actual size:"+size;
   }
    // 某一次运行结果，更多的请看 生产消费者示例com.thread.produceandconsumer.Depot
	//   Thread-0 produce( 60) --> left=  0, inc= 60, size= 60
	//   Thread-1 produce(120) --> left= 80, inc= 40, size=100
	//   Thread-2 consume( 90) <-- left=  0, dec= 90, size= 10
	//   Thread-3 consume(150) <-- left=140, dec= 10, size=  0
	//   Thread-4 produce(110) --> left= 10, inc=100, size=100
	//   Thread-3 consume(150) <-- left= 40, dec=100, size=  0
	//   Thread-4 produce(110) --> left=  0, inc= 10, size= 10
	//   Thread-3 consume(150) <-- left= 30, dec= 10, size=  0
	//   Thread-1 produce(120) --> left=  0, inc= 80, size= 80
	//   Thread-3 consume(150) <-- left=  0, dec= 30, size= 50

}
