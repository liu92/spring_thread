package com.clonede;

/**
 *  clone规则：
 * 1、 基本类型
 如果变量是基本类型，则拷贝其值，比如int、float等。
 2、 对象
 如果变量是一个实例对象，则拷贝其地址引用，也就是说新对象和原来对象是共用实例变量的。
 3、 String字符串
 若变量为String字符串，则拷贝其地址引用。但是在修改时，它会从字符串池中重新生成一个新的字符串，原有的对象保持不变。

 【浅拷贝：对于基本数据类型，实际上是拷贝的它的值，但对于它的对象来说，其实赋值的只是这个对象的引用，
 将原来这个对象的引用传递过去，他们实际上还是指的同一个对象。

 深拷贝：深拷贝会拷贝所有的属性，对于基本数据类型来说 进行值传递，对于引用类型来说，会创建一个新的对象，
 并赋值内容，原来的对象中 引用类型的变化不会影响 拷贝后对象中的 引用类型。】
 *
 *
 * 深克隆（deep clone），深拷贝不仅拷贝对象本身，而且拷贝对象包含的引用指向的所有对象。
 * @ClassName: Test1
 * @Description:
 * @Author: lin
 * @Date: 2019/10/11 8:21
 * History:
 * @<version> 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Address addr = new Address();
        addr.setAdd("杭州市");
        Student1 stu1 = new Student1();
        stu1.setNumber(123);
        stu1.setAddr(addr);
        Student1 stu2 = (Student1) stu1.clone();
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        /**
         * 结果：
         学生1:123,地址:杭州市
         学生2:123,地址:杭州市
         */

        // 乍一看没什么问题，真的是这样吗？
        // 试着改变addr实例的地址
        addr.setAdd("西湖区");
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        /**
         * 学生1:123,地址:杭州市
         学生2:123,地址:杭州市
         学生1:123,地址:西湖区
         学生2:123,地址:西湖区
         *
         * 这就奇怪了，怎么两个学生的地址都改变了？
         * 原因是浅复制只是复制了addr变量的引用，并没有真正的开辟另一块空间，将值复制后再将引用返回给新对象。
         */
    }
}
