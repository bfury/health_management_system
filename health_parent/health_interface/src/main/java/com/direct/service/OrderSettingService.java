package com.direct.service;

import com.direct.pojo.OrderSetting;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:19 下午
 */
public interface OrderSettingService {

    /**
     * 批量导入预约数据
     * @param orderSettings
     */
    void batchInsert(List<OrderSetting> orderSettings);

    /**
     * 按照月份查询预约设置信息
     * @param date
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(String date);

    /**
     * 设置预约信息
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);

}
