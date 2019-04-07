package com.thread.produceandconsumer;

/**
 * 生产/消费者问题是个非常典型的多线程问题，涉及到的对象包括“生产者”、“消费者”、“仓库”和“产品”。他们之间的关系如下：
 *  (01) 生产者仅仅在仓储未满时候生产，仓满则停止生产。
 *  (02) 消费者仅仅在仓储有产品时候才能消费，仓空则等待。 
 *  (03) 当消费者发现仓储没产品可消费时候会通知生产者生产。
 *  (04) 生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 * 
 * 
 * 生成/消费者实现
 * <p>
 * Title:Implementation
 * </p>
 * 
 * @author liuwanlin
 * @date 2018年1月11日下午6:31:54
 */
public class Depot {

	/**
	 * 仓库的容量
	 */
	private int capacity;
	/**
	 * 仓库的实际数量（仓库中当前产品数目）
	 */
	private int size;
	
	
	public Depot(Integer capacity){
		this.capacity=capacity;
		this.size=0;
	}
	
	/** 
	 * 在这里先通过wait()/notify()方式实现该模型(后面在学习了线程池相关内容之后，再通过其它方式实现生产/消费者模型)
	 * 
	 * @Title:

	 * @param val 数量
	 * @author liuwanlin
	 * @date 2018年1月12日下午3:49:18
	 */
	
	
	public synchronized void produce(int val){
		try {
			// left 表示“想要生产的数量”（有可能生产量太多，需多次生产）
			// 比如 val =110
			int left=val;
			while(left>0){
				// 库存已满时，等待“消费者” 消费产品
				// size=0 ,capacity=100;
				while (size>=capacity) {
					wait();
				}
				//获取“实际生成的数量”（即库存中新增的数量）
			    //如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。（此时填满仓库）
				//否则“实际增量”=“想要生产的数量”	
				// 如 0+110>100,则 100-0=100 , inc=100 。
				
				//如果 val=60, 则 size + left<100; 则 left=60，inc=60;
				// 如  0 + 60 > 100 ?  (100 - 0) : 60 所以 inc = 60 ,size =0;
				int inc = (size + left) > capacity ? (capacity - size) : left;
				// 实际数量增加
				size+=inc;
				// 想要生成的量 减少
				left-=inc;
				System.out.printf("%s produce(%3d) --> 想要生成的数量:left=%3d, 实际数量增加:inc=%3d, 仓库的实际数量:size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
				// 通知“消费者”可以消费了。
                notifyAll();//唤醒在此对象监视器上等待的所有线程
			}
		} catch (Exception e) {
		}
	}


	/**
	 * 消费者
	 * @param val 数量
	 */
	public synchronized void consume(int val){
		try {
			// left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
			int left = val;
			while (left>0) {
				// 库存为0时，等待“生产者”生成产品。
				while (size<=0) {
					wait();
				}
					// 获取“实际消费的数量”（即库存中实际减少的数量）
					// 如果“库存”<“客户要消费的数量”，则“实际消费数量”=“库存”；
					// 否则，“实际消费”=“客户要消费的数量”。
				    
				    // 如当  val=150时，size=100 ;  而size=100;是生产者生成的数量（也是库存总容量）。    size<left; dec=100;
					int dec=(size<left)?size:left;
					size-=dec;
					left-=dec;
					System.out.printf("%s consume(%3d) <-- 客户要消费数量:left=%3d, 实际消费的数量:dec=%3d, 是生产者生成的数量:size=%3d\n",
		                        Thread.currentThread().getName(), val, left, dec, size);
					notifyAll();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	@Override
	public String toString(){
		return "capacity:"+capacity+", actual size:"+size;
	}
	
	
	
	
	
	
	
	
}
