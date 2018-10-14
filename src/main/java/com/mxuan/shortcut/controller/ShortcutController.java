package com.mxuan.shortcut.controller;

import com.mxuan.shortcut.data.BaseResponse;
import com.mxuan.shortcut.data.LeftTimeToWorkReq;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/shortcut")
public class ShortcutController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(ShortcutController.class);

    @RequestMapping(value = "leftTimeToWork")
    @ResponseBody
    BaseResponse<Integer> leftTimeToWork(@RequestBody LeftTimeToWorkReq req) {
        logger.info("req : {}", req);

        BaseResponse<Integer> res = new BaseResponse<>();
        List<LeftTimeToWorkReq.EventInfo> eventInfoList = req.getEventInfos();
        long sumTime = eventInfoList.stream()
                .collect(Collectors.summarizingInt(LeftTimeToWorkReq.EventInfo::getTime))
                .getSum();

        boolean matchPosition = eventInfoList
                .stream()
                .anyMatch(eventInfo -> eventInfo.getName().equals(req.getCurrentPosition()));

        if (!matchPosition) {
            res.setErrorCode(1);
            res.setMessage("can not find current position : " + req.getCurrentPosition());
            logger.warn("can not find current position : " + req.getCurrentPosition());
            return res;
        }

        for (LeftTimeToWorkReq.EventInfo eventInfo : eventInfoList) {
            if (eventInfo.getName() == null) {
                logger.error("event name is null");
                continue;
            }
            if (!eventInfo.getName().equals(req.getCurrentPosition())) {
                sumTime -= eventInfo.getTime();
            } else {
                break;
            }
        }

        res.setErrorCode(0);
        res.setMessage("success");
        res.setResult((int) sumTime);
        logger.info("res : {}", res);
        return res;
    }


}
