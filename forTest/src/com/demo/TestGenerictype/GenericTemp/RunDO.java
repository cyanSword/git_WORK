package com.demo.TestGenerictype.GenericTemp;

import java.util.List;

/**
 * Created by yjf on 2018/5/3.
 */
public class RunDO implements GenericTemp<RunDO> {
    Integer Index;
    static  Integer count=0;
    @Override
    public void doTwo(List<? extends RunDO> w) {
        w.stream().forEach(one->{
            one.Index=count++;
        System.out.println(this);
        });
    }

    public RunDO() {
        Index = count++;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RunDO{");
        sb.append(count);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Boolean dosomething(RunDO one) {

        return  this.Index.intValue()>one.Index.intValue();
    }
}
