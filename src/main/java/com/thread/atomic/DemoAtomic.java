package com.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java 多线程  ----- "juc 原子类" 01之框架
 * <p>Title:DemoAtomic</p>
 * @author liuwanlin
 * @date 2018年1月12日下午5:52:39
 */
public class DemoAtomic {

	public static void main(String[] args) {
		
		
		/**
		 * 基本类型: AtomicInteger, AtomicLong, AtomicBoolean ;
		 * 数组类型: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray ; 
		 * 引用类型: AtomicReference, AtomicStampedRerence, AtomicMarkableReference ;
		 * 对象的属性修改类型: AtomicIntegerFieldUpdater, AtomicLongFieldUpdater,
		 * AtomicReferenceFieldUpdater 。
		 * 这些类存在的目的是对相应的数据进行原子操作。所谓原子操作，是指操作过程不会被中断，保证数据操作是以原子方式进行的
		 */
		
		//AtomicInteger

		AtomicInteger at = new AtomicInteger(1);
        System.out.println(at.getAndIncrement());
        System.out.println(at.get());

    }
}
