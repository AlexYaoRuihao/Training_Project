package com.example.demo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MissionOneUser {
        @Id
        @GeneratedValue
        private String id;
        private String pwd;
        private String id_log_flag;

        public MissionOneUser(){
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getId_log_flag() {
           return id_log_flag;
        }

        public void setId_log_flag(String id_log_flag) {
           this.id_log_flag = id_log_flag;
        }
}
