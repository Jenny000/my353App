package com.wang.inis.noticeJpa.repository;

import com.wang.inis.noticeJpa.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements INoticeService{
    @Autowired
    private NoticeRepository noticeRepository;


    @Override
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }
}
