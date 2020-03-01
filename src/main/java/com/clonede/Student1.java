package com.clonede;

/**
 * @ClassName: Student1
 * @Description:
 * @Author: lin
 * @Date: 2019/10/10 23:58
 * History:
 * @<version> 1.0
 */
public class Student1 implements Cloneable{
    private int number;
    private Address addr;

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        Student1 stu = null;
        try {
            stu = (Student1) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    /**
     * 参考地址 https://www.cnblogs.com/qian123/p/5710533.html
     */
}
