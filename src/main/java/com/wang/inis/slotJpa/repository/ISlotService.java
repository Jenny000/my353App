package com.wang.inis.slotJpa.repository;


import com.wang.inis.slotJpa.entity.SlotSet;


public interface ISlotService {
    SlotSet save (SlotSet list);
    SlotSet findSlotsByLastId();

}
