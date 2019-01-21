package com.wang.inis.slotJpa.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Component
@Embeddable
public class Slots {


    @Column(name = "slot_id")
    private String id;
    @Transient
    private String url;
    @Column(name = "slot_k")
    private String k;
    @Column(name = "slot_p")
    private String p;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "slot_cat")
    private String cat;
    @Column(name = "slot_type")
    private String type;
    @Column(name = "slot_date")
    private String date;
    @Column(name = "slot_time")
    private String time;

    public Slots(String cat, String type, String date, String time, String id, String k, String p,Date createTime) {
        this.cat = cat;
        this.type = type;
        this.date = date;
        this.time = time;
        this.id = id;
        this.k = k;
        this.p = p;
        this.createTime = createTime;
    }

    public Slots(String cat, String type, String date, String time, String id, String url, String k, String p, Date createTime) {
        this.cat = cat;
        this.type = type;
        this.date = date;
        this.time = time;
        this.id = id;
        this.url = url;
        this.k = k;
        this.p = p;
        this.createTime = createTime;
    }

    public Slots(String cat, String type, String url, String k, String p, Date createTime) {
        this.cat = cat;
        this.type = type;
        this.url = url;
        this.k = k;
        this.p = p;
        this.createTime = createTime;
    }

    public Slots(String id,String cat, String type){
        this.id = id;
        this.cat = cat;
        this.type = type;

    }

    public Slots(String id, String url, String k, String p, Date createTime, String cat, String type, String date, String time) {
        this.id = id;
        this.url = url;
        this.k = k;
        this.p = p;
        this.createTime = createTime;
        this.cat = cat;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public Slots() {

    }



    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "Slots{" +
                "cat='" + cat + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", k='" + k + '\'' +
                ", p='" + p + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slots slots = (Slots) o;
        return Objects.equals(id, slots.id);
                //Objects.equals(createTime, slots.createTime) &&
                //Objects.equals(cat, slots.cat) &&
                //Objects.equals(type, slots.type) &&
                //Objects.equals(date, slots.date) &&
                //Objects.equals(time, slots.time);
    }

    @Override
    public int hashCode() {
        if(null != id){
            return id.hashCode();

        }
        return 1;
    }
}

