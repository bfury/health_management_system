package com.direct.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.direct.constant.MessageConstant;
import com.direct.entity.Result;
import com.direct.pojo.OrderSetting;
import com.direct.service.OrderSettingService;
import com.direct.utils.DateTimeUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:20 下午
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    /**
     * @param date
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) {
        try {
            List<OrderSetting> settings = orderSettingService.getOrderSettingByMonth(date);
            //数据格式的转换 { date: 1, number: 120, reservations: 1 }
            List<Map<String, Integer>> results = new ArrayList<>(settings.size());
            for (OrderSetting setting : settings) {
                Map<String, Integer> resultMap = new HashMap<String, Integer>(3);
                resultMap.put("date", setting.getOrderDate().getDate());
                resultMap.put("number", setting.getNumber());
                resultMap.put("reservations", setting.getReservations());
                results.add(resultMap);
            }
            return new Result(true, MessageConstant.QUERY_ORDER_SETTING_SUCCESS, results);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_SETTING_FAIL);
        }
    }

    /**
     * @param date
     * @param number
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(String date, Integer number) {
        try {
            OrderSetting orderSetting = new OrderSetting(DateTimeUtil.getDateByString(date, "yyyy-MM-dd"), number);
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.EDIT_ORDER_SETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_ORDER_SETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate1")
    public Result editNumberByDate1(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.EDIT_ORDER_SETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_ORDER_SETTING_FAIL);
        }
    }
}
