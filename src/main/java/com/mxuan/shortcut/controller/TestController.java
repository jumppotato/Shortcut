package com.mxuan.shortcut.controller;

import com.mxuan.shortcut.data.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "getUser")
    @ResponseBody
    User getUser(){
        User user = new User();
        user.setName("小明");
        user.setAge(22);
        return user;
    }
}
