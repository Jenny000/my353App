package com.wang.inis.VisitorIpJpa.repository;

import com.wang.inis.VisitorIpJpa.Entity.VisitorIp;

import java.util.Date;

public interface IVisitorService {
    VisitorIp save(VisitorIp visitorIp);
    void update(Long id);
    Long findByCreate_date(String date,String ip);
}
