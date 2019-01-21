package com.wang.inis.noticeJpa.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
@Component
public class Notice {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private Long id;
    private String importantNotice;
    private String reentryNotice;
    @Id
    private Date date;

    public Notice() {
    }

    public Notice(Date date,String importantNotice, String reentryNotice) {
        this.date = date;
        this.importantNotice = importantNotice;
        this.reentryNotice = reentryNotice;
    }


    public String getImportantNotice() {
        return importantNotice;
    }

    public void setImportantNotice(String importantNotice) {
        this.importantNotice = importantNotice;
    }

    public String getReentryNotice() {
        return reentryNotice;
    }

    public void setReentryNotice(String reentryNotice) {
        this.reentryNotice = reentryNotice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
