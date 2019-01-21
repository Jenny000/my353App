package com.wang.inis.slotJpa.repository;


import com.wang.inis.slotJpa.entity.SlotSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SlotRepository extends JpaRepository<SlotSet,Long>{
    SlotSet save(SlotSet slot);

    @Query(value = "select id from slot_set where id = (select max(id) from slot_set)",
            nativeQuery = true)
    SlotSet findByLastId();
}
