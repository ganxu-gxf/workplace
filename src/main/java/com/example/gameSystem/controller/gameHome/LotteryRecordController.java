package com.example.gameSystem.controller.gameHome;

import com.example.gameSystem.entity.LotteryRecord;
import com.example.gameSystem.service.LotteryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/gameHome/LotteryRecordController")
public class LotteryRecordController {

    @Autowired
    LotteryRecordService lotteryRecordService;

    /**
     * 查询开奖记录
     * @param model
     * @param day
     * @return
     */
    @RequestMapping(value = "/list")
    public String getLotteryRecord(ModelMap model, Integer day){
        if(day < 0 || day > 2){//只能查当天，昨天，前天的数据
            day = 0;
        }
        List<LotteryRecord> lotteryRecordList = lotteryRecordService.getLotteryRecord(day);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i=0;i < lotteryRecordList.size();i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("time", lotteryRecordList.get(i).getEndTime());
            map.put("period", lotteryRecordList.get(i).getPeriod());
            String lotteryNum = lotteryRecordList.get(i).getLotteryNum();
            map.put("lotteryNum", lotteryNum);
            String[] lottery = lotteryNum.split(",");
            int lottery1 = Integer.parseInt(lottery[0]);
            int lottery2 = Integer.parseInt(lottery[1]);
            int lottery3 = Integer.parseInt(lottery[2]);
            int lottery4 = Integer.parseInt(lottery[3]);
            int lottery5 = Integer.parseInt(lottery[4]);
            int sum = lottery1+lottery2+lottery3+lottery4+lottery5;
            map.put("sum", sum);//总和
            if(sum % 2 == 0){
                map.put("singleOrDouble", 2);//双
            }else {
                map.put("singleOrDouble", 1);//单
            }

            if(sum >= 23){
                map.put("bigness", 1);//小
            }else {
                map.put("bigness", 2);//大
            }

            if(lottery1 < lottery5){//虎
                map.put("dragonOrTiger",-1);
            }else if(lottery1 > lottery5){//龙
                map.put("dragonOrTiger",1);
            }else {//和
                map.put("dragonOrTiger",0);
            }

            data.set(i,map);
        }

        long second = (lotteryRecordList.get(lotteryRecordList.size()-1).getEndTime().getTime() - (new Date()).getTime())/1000;
        model.addAttribute("data", data);
        model.addAttribute("second", second);
        model.addAttribute("error", 1);
        model.addAttribute("msg", "成功");
        return "list";
    }


}
