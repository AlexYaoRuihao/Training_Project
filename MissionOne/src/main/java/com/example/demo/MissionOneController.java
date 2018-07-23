package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MissionOneController extends KafkaSender{

    @Autowired
    private MissionOneUserRepository missionOneUserRepository;
    @Autowired
    private MissionOneMarchRepository missionOneMarchRepository;



    //用户凭用户名密码登录
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String type(@RequestParam("id") String Id, @RequestParam("pwd") String pwd) {
        MissionOneUser missionOneUser = new MissionOneUser();
        missionOneUser = missionOneUserRepository.findById(Id).get();
        if (!Id.equals("admin")) {
            if (pwd.equals(missionOneUser.getPwd())) {
                missionOneUserRepository.update_user_id_log_flag(Id);
                return "Logging Success!";
            } else
                return "Logging failed!";
        } else
            return "You have no access to log in as administrator.";
    }


    //POST方法获取所有用户信息
    @PostMapping(value = "/missionone")
    public List<MissionOneUser> missionOneList(@RequestParam("id") String id,
                                               @RequestParam("pwd") String pwd,
                                               @RequestParam("log_off_command") String log_off_command) {
        if (id.equals("admin")) {
            MissionOneUser missionOneUser = new MissionOneUser();
            missionOneUser = missionOneUserRepository.findById(id).get();
            if (pwd.equals(missionOneUser.getPwd()) && log_off_command.equals("N")) {
                missionOneUserRepository.update_user_id_log_flag(id);
                return missionOneUserRepository.findAll();
            } else if (pwd.equals(missionOneUser.getPwd()) && log_off_command.equals("Y")) {
                missionOneUserRepository.update_all_log_flag();
                return null;
            } else
                return null;
        } else
            return null;
    }


    //GET方法查看某人当天/某天所有未/已完成行程
    @RequestMapping(value = "/missiononesee", method = RequestMethod.GET)
    public List<MissionOneMarch> CheckMarch(@RequestParam("user_id") String user_id,
                                            @RequestParam("date") String date,
                                            @RequestParam("finished") String Finished) {
        MissionOneUser missionOneUser = new MissionOneUser();
        missionOneUser = missionOneUserRepository.findById(user_id).get();
        if (missionOneUser.getId_log_flag().equals("1") && !(user_id.equals("admin"))) {
            List<MissionOneMarch> list = missionOneMarchRepository.findByIDandDateandFinished(date, user_id, Finished);
            return list;
        }
        else
            return null;
    }
    //POST方法新增某一天的行程
    @PostMapping(value = "/missiononeadd")
    public MissionOneMarch Add(@RequestParam("Finished") String Finished,
                               @RequestParam("date") String date,
                               @RequestParam("id") Integer id,
                               @RequestParam("MarchName") String MarchName,
                               @RequestParam("user_id") String user_id) {

        MissionOneUser missionOneUser = new MissionOneUser();
        missionOneUser = missionOneUserRepository.findById(user_id).get();
        if (missionOneUser.getId_log_flag().equals("1") && !(user_id.equals("admin"))) {

            MissionOneMarch missionOneMarch = new MissionOneMarch();

            missionOneMarch.setDate(date);
            missionOneMarch.setFinished(Finished);
            missionOneMarch.setId(id);
            missionOneMarch.setMarchName(MarchName);
            missionOneMarch.setUser_id(user_id);

            return missionOneMarchRepository.save(missionOneMarch);
        }
        else
            return null;
    }

    //DELETE方法删除某人某一天的行程
    @DeleteMapping(value = "/missiononedelete")
    public void missiononedelete(@RequestParam("date") String date,
                                 @RequestParam("user_id") String user_id) {
        MissionOneUser missionOneUser = new MissionOneUser();
        missionOneUser = missionOneUserRepository.findById(user_id).get();
        if (missionOneUser.getId_log_flag().equals("1") && !(user_id.equals("admin"))) {
            missionOneMarchRepository.deleteByDateandAndUser_id(date, user_id);
        }
    }


    //PUT方法根据date给行程设置为已完成
    @PutMapping(value = "/missiononeset")
    @ResponseBody
    public void missiononeset(@RequestParam("date") String date,
                              @RequestParam("finished") String finished,
                              @RequestParam("user_id") String user_id) {
        MissionOneUser missionOneUser = new MissionOneUser();
        missionOneUser = missionOneUserRepository.findById(user_id).get();

        if (missionOneUser.getId_log_flag().equals("1") && !(user_id.equals("admin"))) {
            List<MissionOneMarch> list = missionOneMarchRepository.query(user_id, date, finished);
            for (int j = 0; j < list.size(); j++) {
                String msg = "用户 " + list.get(j).getUser_id() + " 于 " + list.get(j).getDate() + " 完成任务 "
                        + list.get(j).getMarchName();
                super.sendTest(msg);
                System.out.println(msg);
            }

            missionOneMarchRepository.update(user_id, date, finished);
        }
    }

}
