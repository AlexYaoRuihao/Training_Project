package com.example.demo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class MissionOneMarch {
    @Id
    @GeneratedValue
    private int id;//行程id为主键

    private String Finished;
    private String date;

    private String MarchName;

    private String user_id;//用户id


    public MissionOneMarch(){
    }

    public String getFinished() {
        return Finished;
    }

    public void setFinished(String finished) {
        Finished = finished;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarchName() {
        return MarchName;
    }

    public void setMarchName(String marchName) {
        MarchName = marchName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
