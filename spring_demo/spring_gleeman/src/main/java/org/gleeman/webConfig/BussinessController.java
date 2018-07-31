package org.gleeman.webConfig;

/**
 * Created by yjf on 2018/7/9.
 */

import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/bussiness")
@RestController
public class BussinessController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result getHello() {
        return ResultUtil.success("万物皆对象，对象是对现实世界事务模型的抽象，为问题的求解提供了一种优美的解决方案\n" +
                "    Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，" +
                "因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以...");
    }
}