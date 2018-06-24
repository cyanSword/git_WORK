package com.demo.TestGenerictype.GenericTemp;

import java.util.List;

/**
 * Created by yjf on 2018/5/3.
 */
//GenericTemp为功能模板
//对自限定类持有的自己的对象应用模板的功能
public class FlyDo implements GenericTemp<FlyDo>{
    @Override
    public Boolean dosomething(FlyDo one) {

        one.fly();
        return false;
    }


    void fly(){
        System.out.println("fly ....");
    }
    @Override
    public void doTwo(List<? extends FlyDo> w) {
        System.out.println("=====all fly=======");
        w.stream().forEach(one->one.fly());
    }
}
