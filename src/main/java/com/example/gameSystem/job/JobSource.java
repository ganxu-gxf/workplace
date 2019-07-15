//package com.example.gameSystem.job;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Slf4j
//@Component
//public class JobSource {
//    @Autowired
//    private ChannelRegisteredService channelRegisteredService;
//    @Autowired
//    GlobalVariableService globalVariableService;
//
//    /**
//     * 定时器，每隔15分钟从17楼渠道表里面拉取个个渠道的注册数
//     */
//
//    @Scheduled(cron = "0 0/10 * * * ?")
//    public void saveSource() {
//        List <ChannelRegistered> channelAll1 = channelRegisteredService.getChannelAll();
//        double conversionRate= globalVariableService.getConversionRate();
//        int datumNumber =(int)globalVariableService.getDatumNumber();
//        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd");
//        String format = s1.format(new Date()) + "%";
//        for (ChannelRegistered channelRegistered : channelAll1) {
//            ChannelRegistered channelRegisteredName = channelRegisteredService.getChannelRegisteredName(channelRegistered.getChannelRegisteredName(), format);
//            if(channelRegisteredName!=null){
//                channelRegistered.setId(channelRegisteredName.getId());
//            }
//            if(Double.parseDouble(channelRegistered.getChannelApplyNumber()) / Double.parseDouble(channelRegistered.getChannelRegisteredNumber()) < conversionRate && Double.parseDouble(channelRegistered.getChannelRegisteredNumber())>datumNumber){
//                int registeredNumber= (int) (Double.parseDouble(channelRegistered.getChannelApplyNumber())/conversionRate);
//                if(registeredNumber<datumNumber){
//                    registeredNumber=datumNumber;
//                }
//                channelRegistered.setChannelRegisteredNumber(registeredNumber+"");
//            }
//            channelRegistered.setChannelDate(new Date());
//            channelRegistered.setCreateDate(new Date());
//            channelRegistered.setModifyDate(new Date());
//        }
//        for (ChannelRegistered channelRegistered : channelAll1) {
//            try {
//                channelRegisteredService.save(channelRegistered);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    /**
//     *每天23:59在保存一次当天的数据
//     */
//    @Scheduled(cron="0 58 23 ? * *")
//    public void saveLastSource() {
//        List <ChannelRegistered> channelAll1 = channelRegisteredService.getChannelAll();
//        int datumNumber =(int)globalVariableService.getDatumNumber();
//        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd");
//        String format = s1.format(new Date()) + "%";
//        double conversionRate= globalVariableService.getConversionRate();
//        for (ChannelRegistered channelRegistered : channelAll1) {
//            ChannelRegistered channelRegisteredName = channelRegisteredService.getChannelRegisteredName(channelRegistered.getChannelRegisteredName(), format);
//            if(channelRegisteredName!=null){
//                channelRegistered.setId(channelRegisteredName.getId());
//            }
//            System.out.println(Double.parseDouble(channelRegistered.getChannelApplyNumber())/Double.parseDouble(channelRegistered.getChannelRegisteredNumber()));
//            if(Double.parseDouble(channelRegistered.getChannelApplyNumber())/Double.parseDouble(channelRegistered.getChannelRegisteredNumber()) < conversionRate && Double.parseDouble(channelRegistered.getChannelRegisteredNumber())>datumNumber){
//                int registeredNumber= (int) (Double.parseDouble(channelRegistered.getChannelApplyNumber())/conversionRate);
//                if(registeredNumber<datumNumber){
//                    registeredNumber=datumNumber;
//                }
//                channelRegistered.setChannelRegisteredNumber(registeredNumber+"");
//            }
//            channelRegistered.setChannelDate(new Date());
//            channelRegistered.setCreateDate(new Date());
//            channelRegistered.setModifyDate(new Date());
//        }
//        for (ChannelRegistered channelRegistered : channelAll1) {
//            try {
//                channelRegisteredService.save(channelRegistered);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}