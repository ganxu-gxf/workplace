package com.example.gameSystem.service;

import com.example.gameSystem.dao.LotteryRecordDao;
import com.example.gameSystem.entity.LotteryRecord;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LotteryRecordService {
    @Autowired
    LotteryRecordDao lotteryRecordDao;

    public List<LotteryRecord> getLotteryRecord(int day){
        String startDay = DateUtils.formatDate(org.apache.commons.lang.time.DateUtils.addDays(new Date(),day),"yyyyMMdd");
        List<LotteryRecord> lotteryRecordList = lotteryRecordDao.findByStartDay(startDay);
        return lotteryRecordList;
    }

    public LotteryRecord getByOrderNo(String orderNo){
        return lotteryRecordDao.findByOrderNo(orderNo);
    }

}
