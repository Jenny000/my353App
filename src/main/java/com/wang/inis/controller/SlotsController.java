package com.wang.inis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.inis.PostComment.repository.IComment;
import com.wang.inis.VisitorIpJpa.Entity.VisitorIp;
import com.wang.inis.VisitorIpJpa.repository.IVisitorService;
import com.wang.inis.slotJpa.entity.SlotSet;

import com.wang.inis.slotJpa.repository.ISlotService;
import com.wang.inis.utils.GetAvailSlots;
import com.wang.inis.utils.VisitorIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class SlotsController {
    @Autowired
    private GetAvailSlots availSlots;

    @Autowired
    private ISlotService slotService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SlotSet slotSet;
    @Autowired
    private VisitorIpUtils visitorIpUtils;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IVisitorService visitorService;
    @Autowired
    private VisitorIp visitorIp;
    @Autowired
    private IComment iComment;




    @RequestMapping("/saveSlots")
    //@Scheduled(fixedDelay = 60 * 1000)
    public void saveSlots(){

        if(0 != availSlots.getAvailSlot().size()){
            slotService.save(new SlotSet(availSlots.getAvailSlot()));
            log.info("slot save successfully");
        }else {
            log.info("No slots, Nothing saved.");
        }

    }

    @RequestMapping(value = "/getSlots")
    public String getSlots(){
        //iComment.findByDate();

        //获取ip
        visitorService.save(visitorIp);


        //获取slots
        if(null != slotSet.getSlotSet()){
            //将数据转化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(slotSet.getSlotSet());
                redisTemplate.expire("slotKey",605, TimeUnit.SECONDS);

                if(null != redisTemplate.opsForValue().get("slotKey")){
                    log.info("slots saved in redis");
                }else{
                    if(null != slotService.findSlotsByLastId()){
                        redisTemplate.opsForValue().set("slotKey",slotService.findSlotsByLastId());
                    }
                    return json;
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }



        }else {
            log.info("no slots available");

        }
        return null;

    }


}
