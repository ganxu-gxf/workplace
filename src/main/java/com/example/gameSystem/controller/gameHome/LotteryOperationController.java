package com.example.gameSystem.controller.gameHome;

import com.example.gameSystem.entity.LotteryOperation;
import com.example.gameSystem.entity.LotteryRecord;
import com.example.gameSystem.service.LotteryOperationService;
import com.example.gameSystem.service.LotteryRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gameHome/LotteryOperationController")
public class LotteryOperationController {

    @Autowired
    LotteryOperationService lotteryOperationService;
    @Autowired
    LotteryRecordService lotteryRecordService;

    /**
     * 保存或编辑配置
     * @param model
     * @param orderNo
     * @param lotteryNum
     * @return
     */
    @RequestMapping(value = "/add")
    public String addOperation(Model model, String orderNo, String lotteryNum){
        if(StringUtils.isBlank(orderNo) || StringUtils.isBlank(lotteryNum)){
            model.addAttribute("error", -1);
            model.addAttribute("msg", "参数错误");
            return "add";
        }
        LotteryOperation lotteryOperation = lotteryOperationService.findByOrderNo(orderNo);
        if(lotteryOperation != null){
            LotteryRecord lotteryRecord = lotteryRecordService.getByOrderNo(orderNo);
            if(lotteryRecord.getStatus() == 1){//已开奖不让修改
                model.addAttribute("error", -1);
                model.addAttribute("msg", "该期已开奖");
                return "add";
            }
        }else {
            lotteryOperation = new LotteryOperation();
        }
        lotteryOperation.setLotteryNum(lotteryNum);
        lotteryOperation.setOrderNo(orderNo);
        lotteryOperationService.save(lotteryOperation);

        model.addAttribute("error", 1);
        model.addAttribute("msg", "保存成功");
        return "add";
    }

    /**
     * 查询开奖配置的列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String listOperation(Model model){
        List<LotteryOperation> lotteryOperationList = lotteryOperationService.findLotteryOperation();
        model.addAttribute("lotteryOperationList", lotteryOperationList);
        model.addAttribute("error", 1);
        model.addAttribute("msg", "查询成功");
        return "list";
    }


}
