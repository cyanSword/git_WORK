package com.demo.TestGenerictype.GenericTemp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjf on 2018/5/3.
 */
public class TestGeneric {
    public static void main(String[] args) {
        FlyDo one = new FlyDo();
        FlyDo two = new FlyDo();
        one.dosomething(one);
        List temp = new ArrayList<FlyDo>();
        temp.add(one);
        temp.add(two);
        one.doTwo(temp);


        RunDO oneRun = new RunDO();
        RunDO twoRun = new RunDO();
        List<RunDO> temp2 = new ArrayList<RunDO>();
        temp2.add(oneRun);
        temp2.add(twoRun);
        System.out.println(oneRun.dosomething(twoRun));

        oneRun.doTwo(temp2);

        temp2.stream().forEach(ele -> {
            System.out.println(ele.dosomething(new RunDO()));
        });



    }


}
