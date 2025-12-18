package com.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ByeController {

    @RequestMapping("bye")
    public String sayBye(){
        return "redirect:http://www.baidu.com";
    }
}
