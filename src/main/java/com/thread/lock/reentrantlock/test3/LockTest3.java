package com.thread.lock.reentrantlock.test3;
/**
 * 示例3，使用condition   去解决“示例1”中的两个问题，"仓库的容量不可能为负数" 以及"仓库的容量是有限制的"
 * <p>Title:LockTest3</p>
 * @author liuwanlin
 * @date 2018年2月23日上午11:32:42
 */
public class LockTest3 {
	public static void main(String[] args) {
     Depot3 depot3 = new Depot3(100);
	 Producer3 producer3 = new Producer3(depot3);
     Customer3 customer3 = new Customer3(depot3);
     producer3.produce(60);
     producer3.produce(120);
     customer3.consume(90);
     customer3.consume(150);
     producer3.produce(110);

    }

    /**
     * 某一次运行结果
     pool-1-thread1 produce( 60) --> 想要生成的数量:left=  0, 实际数量增加:inc= 60, 仓库的实际数量:size= 60
     pool-2-thread1 produce(120) --> 想要生成的数量:left= 80, 实际数量增加:inc= 40, 仓库的实际数量:size=100
     pool-3-thread1 consume( 90) <-- 客户要消费数量:left=  0, 实际消费的数量:dec= 90, 仓库剩余的实际数量:size= 10
     pool-4-thread1 consume(150) <-- 客户要消费数量:left=140, 实际消费的数量:dec= 10, 仓库剩余的实际数量:size=  0
     pool-5-thread1 produce(110) --> 想要生成的数量:left= 10, 实际数量增加:inc=100, 仓库的实际数量:size=100
     pool-4-thread1 consume(150) <-- 客户要消费数量:left= 40, 实际消费的数量:dec=100, 仓库剩余的实际数量:size=  0
     pool-5-thread1 produce(110) --> 想要生成的数量:left=  0, 实际数量增加:inc= 10, 仓库的实际数量:size= 10
     pool-4-thread1 consume(150) <-- 客户要消费数量:left= 30, 实际消费的数量:dec= 10, 仓库剩余的实际数量:size=  0
     pool-2-thread1 produce(120) --> 想要生成的数量:left=  0, 实际数量增加:inc= 80, 仓库的实际数量:size= 80
     pool-4-thread1 consume(150) <-- 客户要消费数量:left=  0, 实际消费的数量:dec= 30, 仓库剩余的实际数量:size= 50
     */

    /**
     *  这个结果是使用一个condition 实例
     pool-1-thread1 produce( 60) --> 想要生成的数量:left=  0, 实际数量增加:inc= 60, 仓库的实际数量:size= 60
     pool-2-thread1 produce(120) --> 想要生成的数量:left= 80, 实际数量增加:inc= 40, 仓库的实际数量:size=100
     pool-3-thread1 consume( 90) <-- 客户要消费数量:left=  0, 实际消费的数量:dec= 90, 仓库剩余的实际数量:size= 10
     pool-4-thread1 consume(150) <-- 客户要消费数量:left=140, 实际消费的数量:dec= 10, 仓库剩余的实际数量:size=  0
     pool-5-thread1 produce(110) --> 想要生成的数量:left= 10, 实际数量增加:inc=100, 仓库的实际数量:size=100
     pool-4-thread1 consume(150) <-- 客户要消费数量:left= 40, 实际消费的数量:dec=100, 仓库剩余的实际数量:size=  0
     pool-5-thread1 produce(110) --> 想要生成的数量:left=  0, 实际数量增加:inc= 10, 仓库的实际数量:size= 10
     pool-2-thread1 produce(120) --> 想要生成的数量:left=  0, 实际数量增加:inc= 80, 仓库的实际数量:size= 90
     pool-4-thread1 consume(150) <-- 客户要消费数量:left=  0, 实际消费的数量:dec= 40, 仓库剩余的实际数量:size= 50
     */
}
