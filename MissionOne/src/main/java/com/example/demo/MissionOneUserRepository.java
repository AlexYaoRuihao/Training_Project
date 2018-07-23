package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface MissionOneUserRepository extends JpaRepository<MissionOneUser, String>{
    @Modifying
    @Transactional
    @Query(value = "update mission_one_user set id_log_flag ='1' where id =?1", nativeQuery = true)
        //在所有与用户名匹配的MySQL记录里更新登录状态为1(已登录)
    void update_user_id_log_flag(String id);

    @Query(value = "select * from mission_one_user where id =?1", nativeQuery = true)
    void querybyid(String id);


    @Modifying
    @Transactional
    @Query(value = "update mission_one_user set id_log_flag ='0'", nativeQuery = true)
    void update_all_log_flag();
}
