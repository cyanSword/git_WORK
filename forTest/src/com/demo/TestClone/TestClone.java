package com.demo.TestClone;

/**
 * Created by yjf on 2018/5/14.
 */
public class TestClone {

    public static void main(String[] args) {
        Demo demo = new SubDemo();//对象向上转型，接口功能隐藏，Demo类并无clone能力
        SubDemo subDemo=null;
        if (demo instanceof Cloneable) {//探测此对象是否与Cloneable句柄链接
            try {
                 subDemo = (SubDemo) ((SubDemo) demo).clone();//若此对象是可以克隆的对象，便对其克隆
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        System.out.print(subDemo);

    }

}
