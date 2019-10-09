package com.std;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StringDemo
 * Description: String在jvm中的实现机制
 * Author:   lin
 * Date:     2019/3/11 17:58
 * History:
 * <version> 1.0
 */
public class StringDemo {

    public static void main(String[] args) {
        List<String> s1 = new LinkedList<>();
        List<Integer> s2 = new LinkedList<>();
        System.out.println(s1.getClass() == s2.getClass());
        // 返回java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("MAX_MEMORY = "+ maxMemory+ " (字节)、"+ (maxMemory/(double)1024/2024)+ "MB");
        System.out.println("TOTAL_MEMORY = "+ totalMemory+ " (字节)、"+ (totalMemory/(double)1024/2024)+ "MB");


        System.out.println("cpu处理数目 = "+Runtime.getRuntime().availableProcessors());

        Test t = new Test();
        Map<Test, String> map = new HashMap<>(16);
        map.put(t, "111");

        /**
         * 在使用 String 类型的对象做 key 时我们可以只根据传入的字符串内容就能获得对应存在 map 中的 value 值，
         * 而非 String 类型的对象在获得对应的 value 时需要的条件太过苛刻，首先要保证散列码相同，
         * 并且经过 equals() 方法判断为 true 时才可以获得对应的 value。
         */
        System.out.println("使用对象来作为map key "+ map.get(new Test()));
//        Arrays.sort();
    }

    /**
     *
      */
    static class  Test {

 }
}
