package com.clonede;

/**
 * clone规则：
 * 1、 基本类型
       如果变量是基本类型，则拷贝其值，比如int、float等。
 2、 对象
      如果变量是一个实例对象，则拷贝其地址引用，也就是说新对象和原来对象是共用实例变量的。
 3、 String字符串
      若变量为String字符串，则拷贝其地址引用。但是在修改时，它会从字符串池中重新生成一个新的字符串，原有的对象保持不变。

 链接：https://juejin.im/post/59bfc707f265da0646188bca

 【浅拷贝：对于基本数据类型，实际上是拷贝的它的值，但对于它的对象来说，其实赋值的只是这个对象的引用，
 将原来这个对象的引用传递过去，他们实际上还是指的同一个对象。

 深拷贝：深拷贝会拷贝所有的属性，对于基本数据类型来说 进行值传递，对于引用类型来说，会创建一个新的对象，
 并赋值内容，原来的对象中 引用类型的变化不会影响 拷贝后对象中的 引用类型。】

 *
 * (1)浅克隆（shallow clone），浅拷贝是指拷贝对象时仅仅拷贝对象本身和对象中的基本变量，而不拷贝对象包含的引用指向的对象。
   (2)深克隆（deep clone），深拷贝不仅拷贝对象本身，而且拷贝对象包含的引用指向的所有对象。
 * @ClassName: Test
 * @Description:
 * @Author: lin
 * @Date: 2019/10/10 23:55
 * History:
 * @<version> 1.0
 */
public class Test {



    public static void main(String args[]) {
//        Student stu1 = new Student();
//        stu1.setNumber(12345);
//        Student stu2 = (Student)stu1.clone();
//
//        System.out.println("学生1:" + stu1.getNumber());
//        System.out.println("学生2:" + stu2.getNumber());
//
//        //注意这里 设置了 值
//        stu2.setNumber(54321);
//
//
//
//        System.out.println("学生1:" + stu1.getNumber());
//        System.out.println("学生2:" + stu2.getNumber());
//
//        System.out.println(stu1 == stu2);

        //测试 计算
//        int COUNT_BITS = Integer.SIZE - 3;
//
//        System.out.println(COUNT_BITS);
//        int RUNNING    = -1 << COUNT_BITS;
//        System.out.println(RUNNING);

        Fex x = new Fex();
        int to = x.to(1);
        System.out.println(to);

    }

    /**
     * 结果：

     学生1:12345

     学生2:12345

     学生1:12345

     学生2:54321

     如果你还不相信这两个对象不是同一个对象，那么你可以看看这一句：
     System.out.println(stu1 == stu2); // false

     上面的复制被称为浅克隆。
     */


    static class  Fex{
          volatile int corePoolSize=2;


          volatile int maximumPoolSize = 4;

      int to(int x){
          boolean c = false;
          int t = c ? corePoolSize: maximumPoolSize;
          return  t;
      }

    }



}
