package com.example.gameSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 开奖记录
 */
@Entity
@Table(name = "t_lottery_record")
public class LotteryRecord extends BaseEntity <Long> {
    private String lotteryNum;              //开奖号码，用逗号隔开
    private long period;                    //开奖期数
    private String orderNo;                 //开奖流水号
    @Column(nullable = false)
    private int status = 0;                 //是否已开奖 0 未开奖，1 已开奖
    private Date startTime;                 //本期开始时间
    private Date endTime;                   //本期结束时间，即开奖时间
    private String startDay;                //记录时间的年月日

    public LotteryRecord(){super();}

    public String getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(String lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
}
