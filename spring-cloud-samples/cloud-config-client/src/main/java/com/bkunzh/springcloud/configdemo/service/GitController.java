package com.bkunzh.springcloud.configdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bkunzh
 * @date 2020/10/8
 */
@RestController
public class GitController {
    @Autowired
    private GitConfig gitConfig;

    @Autowired
    private GitConfig2 gitConfig2;

    @GetMapping(value = "/show")
    public Object show(){
        return gitConfig;
    }

    @GetMapping(value = "/show2")
    public Object show2(){
        return gitConfig2;
    }
}
