package com.wang.inis.noticeJpa.repository;

import com.wang.inis.noticeJpa.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
    Notice save(Notice notice);

}
