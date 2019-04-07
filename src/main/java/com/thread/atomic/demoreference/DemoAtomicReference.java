package com.thread.atomic.demoreference;

import java.util.concurrent.atomic.AtomicReference;

/**
     *  java类简单作用描述
     * @ProjectName:    DemoAtomicReference.java
     * @Package:        com.thread.atomic
     * @ClassName:      DemoAtomicReference
     * @Description:    java类作用描述
     * @Author:         作者姓名
     * @CreateDate:     2018/11/23 0023 11:33
     * @UpdateUser:     作者姓名
     * @UpdateDate:     2018/11/23 0023 11:33
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */

public class DemoAtomicReference {
    public static void main(String[] args) {
        Preson p1 = new Preson(101);
        Preson p2 = new Preson(102);

        AtomicReference ar = new AtomicReference(p1);
        // 如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        ar.compareAndSet(p1,p2);
        Preson p3 = (Preson) ar.get();
        System.out.println("p3 is =" + p3);
        System.out.println("p3.equals(p1)="+p3.equals(p1));
    }
}
