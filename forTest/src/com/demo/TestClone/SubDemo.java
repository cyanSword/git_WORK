package com.demo.TestClone;

/**
 * Created by yjf on 2018/5/14.
 */
public class SubDemo extends Demo implements Cloneable {
    private String  SubId = "sasaas";
    private Demo  demo = null;
    @Override
    public Object clone() throws CloneNotSupportedException {
        SubDemo cloneOne = (SubDemo) super.clone();
        ((Demo)cloneOne.demo).clone();
        return cloneOne;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubDemo{");
        sb.append("SubId='").append(SubId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
