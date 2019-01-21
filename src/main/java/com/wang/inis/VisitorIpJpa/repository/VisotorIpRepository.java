package com.wang.inis.VisitorIpJpa.repository;

import com.wang.inis.VisitorIpJpa.Entity.VisitorIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public interface VisotorIpRepository extends JpaRepository<VisitorIp,Long> {
    VisitorIp save(VisitorIp visitorIp);


    @Query(value = "select id from visitor_ip where create_date = ? and ip = ?", nativeQuery = true)
    Long findByCreate_date (String date, String ip);


    //@Transactional @Modifying 需要加上这两段注解，否则会处出错误Can not issue data manipulation statements with executeQuery()，
    @Modifying
    @Transactional //不加会出现错误     javax.persistence.TransactionRequiredException: Executing an update/delete query
    @Query(value = "update visitor_ip set count = count +1 where id = ?",nativeQuery = true)
    void update(Long id);
}
