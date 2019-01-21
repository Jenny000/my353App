package com.wang.inis.slotJpa.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
public class SlotSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "Slot")

    private Set<Slots> slotSet;

    public SlotSet() {

    }

    public SlotSet(Set<Slots> slotSet) {
        this.slotSet = slotSet;
    }

    public SlotSet(Long id, Set<Slots> slotSet) {
        this.id = id;
        this.slotSet = slotSet;
    }

    public Set<Slots> getSlotSet() {
        return slotSet;
    }

    public void setSlotSet(Set<Slots> slotSet) {
        this.slotSet = slotSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
