package com.wang.inis.VisitorIpJpa.repository;

import com.wang.inis.VisitorIpJpa.Entity.VisitorIp;
import com.wang.inis.utils.VisitorIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class VisitorServiceImpl implements IVisitorService {
    @Autowired
    private VisotorIpRepository visotorIpRepository;
    @Autowired
    private VisitorIp visitorIp;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private VisitorIpUtils visitorIpUtils;
    

    @Override
    public VisitorIp save(VisitorIp vIp) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        String ip = visitorIpUtils.getVisitorIpAddr(request);
        Long id = findByCreate_date(todayDate,ip);
        if(null != ip){
            if(null != id){
                int count = visitorIp.getCount();
                System.out.println("count   " +count);
                visotorIpRepository.update(id);
                log.info("ip count update successfully");
            }else {
                visotorIpRepository.save(new VisitorIp(visitorIp.getIp(),visitorIp.getCreate_date(),visitorIp.getCreate_time(),visitorIp.getCount()));
                log.info("ip saved successfully");

            }

        }

        return null;
    }

    @Override
    public void update(Long id) {
    }

    @Override
    public Long findByCreate_date(String date, String ip) {
        return visotorIpRepository.findByCreate_date(date,ip);
    }


}
