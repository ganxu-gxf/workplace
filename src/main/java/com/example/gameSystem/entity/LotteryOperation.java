package com.example.gameSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_lottery_operation")
public class LotteryOperation extends BaseEntity<Long> {
    @Column(unique = true)
    private String orderNo;        //开奖流水号
    private String lotteryNum;      //开奖号码

    public LotteryOperation(){super();}

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(String lotteryNum) {
        this.lotteryNum = lotteryNum;
    }
}
