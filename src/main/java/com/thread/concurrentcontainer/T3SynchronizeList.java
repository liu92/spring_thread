package com.thread.concurrentcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这种是 加了锁的
 * @ClassName: T3SynchronizeList
 * @Description:
 * @Author: lin
 * @Date: 2019/11/23 20:56
 * History:
 * @<version> 1.0
 */
public class T3SynchronizeList {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        // 这个返回的list 是加了锁的
        List<String> strSync = Collections.synchronizedList(str);
    }

}
