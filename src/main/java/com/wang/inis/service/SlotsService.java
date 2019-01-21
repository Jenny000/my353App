package com.wang.inis.service;

import com.wang.inis.slotJpa.entity.Slots;

import com.wang.inis.slotJpa.repository.ISlotService;
import com.wang.inis.utils.GetAvailSlots;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
/**
public class SlotsService {

    @Autowired
    private GetAvailSlots getAvailSlots;
    @Autowired
    private ISlotService slotService;



    //Set<FutureTask<SlotsList>> futureTask = new HashSet<>();
    Set<SlotsList> setList = new HashSet<>();

    public void getSlots(){
        setList.removeAll(setList);
        GetSet gs = new GetSet();

        ExecutorService service = Executors.newFixedThreadPool(2);

        for(int i=0;i<2;i++){
                //FutureTask<SlotsList> task = new FutureTask<>(gs);
                System.out.println("11111111111111111111111111111111");

                // 提交futureTask对象进入线程池
                service.submit(gs);
            }

        service.shutdown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务....");
        System.out.println("size " + setList.size());

        for(SlotsList sl: setList){
            System.out.println("list   " + sl);


        }
        System.out.println("所有任务执行完毕");


    }




    private class GetSet implements Callable<Set> {
        private SlotsList list;

        @Override
        public Set call(){

            System.out.println("子线程在进行....");



            if(0 != getAvailSlots.getSlot().size()){
                list = new Slots(getAvailSlots.getSlot()));
                //setList.add(new SlotsList(getAvailSlots.getAvailSlots()));
                setList.add(list);

            }else {
                log.info("List is Empty");

            }
            return list;
        }
    }


}

**/
