package com.thread.atomic;

import java.util.concurrent.atomic.AtomicLongArray;

/**
     *  java类简单作用描述
     * @ProjectName:    DemoAtomicLongArray.java
     * @Package:        com.thread.atomic
     * @ClassName:      DemoAtomicLongArray
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/22 0022 18:59
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/22 0022 18:59
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoAtomicLongArray {
    public static void main(String[] args) {
        long[] arrLong = new long[]{10,20,30,40};

        AtomicLongArray tomiLongArray = new AtomicLongArray(arrLong);

        //  将位置 i 的元素设置为给定值。给指定的位置设置指定的值
        tomiLongArray.set(0,100);

        for (int i = 0; i <tomiLongArray.length() ; i++) {
            System.out.printf("get(%d) : %s\n", i, tomiLongArray.get(i));
        }
        // 以原子方式将索引 i 的元素减 1。
        System.out.printf("%20s : %s\n", "getAndDecrement(0)", tomiLongArray.getAndDecrement(0));
        // 以原子方式将索引 i 的元素减 1。
        System.out.printf("%20s : %s\n", "decrementAndGet(1)", tomiLongArray.decrementAndGet(1));
        // 以原子方式将索引 i 的元素加 1。
        System.out.printf("%20s : %s\n", "getAndIncrement(2)", tomiLongArray.getAndIncrement(2));
        // 以原子方式将索引 i 的元素加 1。
        System.out.printf("%20s : %s\n", "incrementAndGet(3)", tomiLongArray.incrementAndGet(3));
        // 以原子方式将给定值添加到索引 i 的元素。
        System.out.printf("%20s : %s\n", "addAndGet(100)", tomiLongArray.addAndGet(0,100));
        // 以原子方式将给定值与索引 i 的元素相加。
        System.out.printf("%20s : %s\n", "getAndAdd(100)", tomiLongArray.getAndAdd(1, 100));
        // 如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        System.out.printf("%20s : %s\n", "compareAndSet()", tomiLongArray.compareAndSet(2, 31, 1000));
        // 获取位置 i 的当前值。
        System.out.printf("%20s : %s\n", "get(2)", tomiLongArray.get(2));
    }
}
