package com.wang.inis.controller;

import com.wang.inis.noticeJpa.entity.Notice;
import com.wang.inis.noticeJpa.repository.INoticeService;
import com.wang.inis.utils.JsoupGetNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @Autowired
    private JsoupGetNotice jsoupGetNotice;
    @Autowired
    private INoticeService noticeService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0 0,9 * * ?" )
    @RequestMapping("/getNotice")
    public Notice getNotice(){
        Notice notice = jsoupGetNotice.getNoticeForHomePageAndReentryVisa();
        if(null != notice){
            noticeService.save(notice);
            return notice;
        }else {
            logger.error("Notice is empty");

        }
        return null;
    }
}
