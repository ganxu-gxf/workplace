package com.example.gameSystem.dao;

import com.example.gameSystem.entity.LotteryOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryOperationDao extends JpaRepository<LotteryOperation, Long> {

    LotteryOperation findByOrderNo(String orderNo);

    /**
     * 查询出开奖配置列表，包括是否开奖
     * @return
     */
    @Query(value = "SELECT lo.*,lr.`status` AS `status` FROM t_lottery_operation lo LEFT JOIN t_lottery_record lr ON lo.order_no = lr.order_no ORDER BY lo.order_no DESC", nativeQuery = true)
    List<LotteryOperation> getLotteryOperation();

}
