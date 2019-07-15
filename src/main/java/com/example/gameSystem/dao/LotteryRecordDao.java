package com.example.gameSystem.dao;

import com.example.gameSystem.entity.LotteryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryRecordDao extends JpaRepository<LotteryRecord, Long> {

    @Query(value = "SELECT * FROM t_lottery_record WHERE DATE_FORMAT(DATE_SUB(lottery_time,INTERVAL 1 MINUTE),'%Y-%m-%d') = ? AND lottery_time <= NOW() ORDER BY lottery_time DESC", nativeQuery = true)
    List<LotteryRecord> findByStartDay(String lotteryTime);

    LotteryRecord findByOrderNo(String orderNo);

}
