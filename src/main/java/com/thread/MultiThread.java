package com.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;

/**
 * 使用JMX来查看一个普通的java程序包含哪些线程
 * @author
 */
public class MultiThread {
    public static void main(String[] args) {

        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        List<ThreadInfo> threadInfoList = Arrays.asList(threadInfos);
        threadInfoList.forEach(threadInfo -> System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName()));
        /**
         * [6]Monitor Ctrl-Break
           [5]Attach Listener
           [4]Signal Dispatcher // 分发处理发送给JVM信号的线程
           [3]Finalizer         // 调用对象finalizer方法的方法
           [2]Reference Handler //清除Reference的线程
           [1]main              // main线程，用户程序入口
         */
    }
}
