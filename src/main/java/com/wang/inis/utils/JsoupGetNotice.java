package com.wang.inis.utils;

import com.wang.inis.noticeJpa.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class JsoupGetNotice {
    @Autowired
    private Notice notice;

    private static final String noticeUrl = "http://www.inis.gov.ie";
    private static final String reentryUrl = "http://www.inis.gov.ie/en/INIS/Pages/visas-reentry-decisions";

    public Notice getNoticeForHomePageAndReentryVisa() {

        try {
            Document imptNotice = Jsoup.connect(noticeUrl).get();
            Document reentryNotice = Jsoup.connect(reentryUrl).get();
            Elements importantNotice = imptNotice.getElementsByClass("top-div");
            Elements reEntryNotice = reentryNotice.getElementsByClass("roadmap1");
            String imNotice = String.valueOf(importantNotice);
            String entryNotice = String.valueOf(reEntryNotice);
            notice.setImportantNotice(imNotice);
            notice.setReentryNotice(entryNotice);
            notice.setDate(new Date());

            System.out.println(imNotice);
            System.out.println(entryNotice);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Jsoup notice IO错误");

        }

        return new Notice(notice.getDate(),notice.getImportantNotice(),notice.getReentryNotice());

    }

}
