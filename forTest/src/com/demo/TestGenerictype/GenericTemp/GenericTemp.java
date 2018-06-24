package com.demo.TestGenerictype.GenericTemp;

import java.util.List;

/**
 * Created by yjf on 2018/5/3.
 */
public interface GenericTemp<T extends GenericTemp> {
            public  Boolean dosomething( T one);

             //上界限定的List进行处理
            public  void doTwo(List<? extends T> w);
}
