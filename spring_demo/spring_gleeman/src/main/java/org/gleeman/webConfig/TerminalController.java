package org.gleeman.webConfig;

/**
 * Created by yjf on 2018/7/9.
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "terminal")
@RestController
public class TerminalController {

    @GetMapping("world")
    public Result getHello() {
        return ResultUtil.success("hello world");
    }
}
