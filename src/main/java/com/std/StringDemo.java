package com.std;

import java.util.LinkedList;
import java.util.List;

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

//        Arrays.sort();
    }
}
