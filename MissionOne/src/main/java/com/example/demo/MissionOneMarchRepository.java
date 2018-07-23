package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MissionOneMarchRepository extends JpaRepository<MissionOneMarch, String> {
    @Modifying
    @Transactional
    @Query(value = "delete from mission_one_march where date =?1 and user_id =?2", nativeQuery = true)
        // 删除与行程日期和用户名匹配的MySQL记录
    void deleteByDateandAndUser_id(String date, String user_id);
    //void deleteByDate(String date);

    @Query(value = "select * from `mission_one_march` where `date` =?1 and `user_id` =?2 and `finished` =?3", nativeQuery = true)
    //查找与行程日期，行程状态和用户名匹配的MySQL记录
    List<MissionOneMarch> findByIDandDateandFinished(String date, String user_id, String Finished);

    @Modifying
    @Transactional
    @Query(value = "update mission_one_march set finished ='Y' where user_id =?1 and date =?2 and finished =?3", nativeQuery = true)
    //在所有与用户名，行程日期和形成状态匹配的MySQL记录里更新行程状态为'Y'
    void update(String user_id, String date, String finished);

    @Query(value = "select * from mission_one_march where user_id =?1 and date =?2 and finished =?3", nativeQuery = true)
    List<MissionOneMarch> query(String user_id, String date, String finished);




}
