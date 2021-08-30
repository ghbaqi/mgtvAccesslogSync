package com.ppio.third.mgtv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    int i = 0;

    @ResponseBody
    @GetMapping("/hi")
    public String hi() {
        return "hi " + i++;
    }


}
