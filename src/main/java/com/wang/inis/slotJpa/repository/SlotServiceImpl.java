package com.wang.inis.slotJpa.repository;

import com.wang.inis.slotJpa.entity.SlotSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SlotServiceImpl implements ISlotService{

    @Autowired
    private SlotRepository slotRepository;


    @Override
    public SlotSet save(SlotSet set) {

        return slotRepository.save(set);
    }

    @Override
    public SlotSet findSlotsByLastId() {
        return slotRepository.findByLastId();
    }
}