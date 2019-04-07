package com.thread.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
     *  java类简单作用描述
     * @ProjectName:    DemoAtomicLong.java
     * @Package:        com.thread.atomic
     * @ClassName:      DemoAtomicLong
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/22 0022 17:56
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/22 0022 17:56
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoAtomicLong {

    /**
      AtomicLong 是对长整型进行原子操作
       在32位操作系统中，64位的long 和 double 变量由于会被JVM当作两个分离的32位来进行操作，
       所以不具有原子性。而使用AtomicLong能让long的操作保持原子型。
    */

    public static void main(String[] args) {
        AtomicLong mLong = new AtomicLong();

        // 设置值
        mLong.set(0x0123456789ABCDEFL);
        // 获取当前值
        System.out.printf("%20s: 0x%016X\n","get()",mLong.get());
        // 返回当前值对应的int值
        System.out.printf("%20s: 0x%016X\n","intValue()",mLong.intValue());
        // 获取当前值对应的long值
        System.out.printf("%20s: 0x%016X\n","longValue()",mLong.longValue());
        // 获取当前值对应的double值
        System.out.printf("%20s: %s\n","doubleValue()",mLong.doubleValue());
        // 获取当前值对应的floatValue值
        System.out.printf("%20s: %s\n","floatValue()",mLong.floatValue());
        // 获取当前值对应的getAndDecrement值 , 以原子方式将当前值减 1，并返回减1前的值。等价于“num--”
        System.out.printf("%20s : 0x%016X\n", "getAndDecrement()", mLong.getAndDecrement());
        // 以原子方式将当前值减 1，并返回减1后的值。等价于“--num”
        System.out.printf("%20s : 0x%016X\n", "decrementAndGet()", mLong.decrementAndGet());
        // 以原子方式将当前值加 1，并返回加1前的值。等价于“num++”
        System.out.printf("%20s : 0x%016X\n", "getAndIncrement()", mLong.getAndIncrement());
        // 以原子方式将当前值加 1，并返回加1后的值。等价于“++num”
        System.out.printf("%20s : 0x%016X\n", "incrementAndGet()", mLong.incrementAndGet());
        // 以原子方式将delta与当前值相加，并返回相加后的值。
        System.out.printf("%20s : 0x%016X\n", "addAndGet(0x10)", mLong.addAndGet(0x10));
        // 以原子方式将delta添加到当前值，并返回相加前的值。
        System.out.printf("%20s : 0x%016X\n", "getAndAdd(0x10)", mLong.getAndAdd(0x10));

        System.out.printf("\n%20s : 0x%016X\n", "get()", mLong.get());
        // 如果当前值 == expect，则以原子方式将该值设置为update。成功返回true，否则返回false，并且不修改原值。
        System.out.printf("%20s : %s\n", "compareAndSet()", mLong.compareAndSet(0x12345679L, 0xFEDCBA9876543210L));
        System.out.printf("%20s : 0x%016X\n", "get()", mLong.get());
    }

}


