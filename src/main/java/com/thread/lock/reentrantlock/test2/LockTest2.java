package com.thread.lock.reentrantlock.test2;
/**
 * 
 * <p>Title:LockTest2</p>
 * @author liuwanlin
 * @date 2018年2月12日下午6:27:56
 */
public class LockTest2 {
  public static void main(String[] args) {
	 Depot2 depot2 = new Depot2();
	 Producer2 producer2 = new Producer2(depot2);
	 Customer2 customer2 = new Customer2(depot2);
	 
	 producer2.produce(60);
	 producer2.produce(120);
	 customer2.consume(90);
	 customer2.consume(150);
	 producer2.produce(110);
  }
  
  
 /**
  Thread-0 produce(60) --> size=-60
  Thread-1 produce(120) --> size=-60
  Thread-2 consume(90) <-- size=-60
  Thread-3 consume(150) <-- size=-60
  Thread-4 produce(110) --> size=50

  */
  /**
   * 结果说明：
   * 示例2在示例1的基础上 去掉了 lock,在“示例2”中，
   * 仓库中最终剩余的产品是-60，而不是我们期望的50。原因是我们没有实现对仓库的互斥访问。
   *
   *
   * 使用线程池 执行结果如下
   pool-1-thread1 produce(60) --> size=60
   pool-5-thread1 produce(110) --> size=50
   pool-4-thread1 consume(150) <-- size=-60
   pool-3-thread1 consume(90) <-- size=90
   pool-2-thread1 produce(120) --> size=180

   结果可以看出，如果没有仓库进行互斥访问，那么就会造成仓库数量为负数。这就不符合情理
   我们都明白一般卖东西时只能仓库有存货，我们才去卖。难道没有商品时候回去卖吗。同样的买东西 要做仓库中有产品是我们才能买到。
   并且买的产品数量不能超过库存，如果超过了库存数量，我想买多买点产品 也没有办法，这时只能更新实际情况去生产商品。卖给需要的
   消费者。
   * 
   */
}
