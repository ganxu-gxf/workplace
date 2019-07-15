package com.example.gameSystem.service;

import com.example.gameSystem.dao.LotteryOperationDao;
import com.example.gameSystem.entity.LotteryOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LotteryOperationService {

    @Autowired
    LotteryOperationDao lotteryOperationDao;

    /**
     * 保存开奖配置
     * @param lotteryOperation
     */
    public void save(LotteryOperation lotteryOperation){
        lotteryOperationDao.save(lotteryOperation);
    }

    /**
     * 通过期数查询开奖配置
     * @param orderNo
     * @return
     */
    public LotteryOperation findByOrderNo(String orderNo){
        return lotteryOperationDao.findByOrderNo(orderNo);
    }

    public List<LotteryOperation> findLotteryOperation(){
        return lotteryOperationDao.getLotteryOperation();
    }

}
