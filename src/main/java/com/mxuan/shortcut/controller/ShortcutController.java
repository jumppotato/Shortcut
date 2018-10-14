package com.mxuan.shortcut.controller;

import com.mxuan.shortcut.data.BaseResponse;
import com.mxuan.shortcut.data.LeftTimeToWorkReq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/shortcut")
public class ShortcutController {

    @RequestMapping(value = "leftTimeToWork")
    @ResponseBody
    BaseResponse<Integer> leftTimeToWork(@RequestBody LeftTimeToWorkReq req) {
        BaseResponse<Integer> res = new BaseResponse<>();
        System.out.println("res = " + res);
        return res;
    }


}
