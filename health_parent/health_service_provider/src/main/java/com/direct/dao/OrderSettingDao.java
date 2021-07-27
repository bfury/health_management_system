package com.direct.dao;

import com.direct.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:22 下午
 */
public interface OrderSettingDao {
    /**
     * 查询对应的日期是否已经设置过预约信息
     * @param orderDate
     * @return
     */
    Long findCountByOrderDate(Date orderDate);

    /**
     * 通过预约日期更新可预约人数
     * @param setting
     */
    void updateNumberByOrderDate(OrderSetting setting);

    /**
     * 保存预约设置信息
     * @param setting
     */
    void add(OrderSetting setting);

    /**
     * 按照月份查询预约设置数据
     * @param date
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(String date);

    /**
     * 通过日期查询预约设置信息
     * @param date
     * @return
     */
    OrderSetting findByOrderDate(Date date);

    /**
     * 按照日期更新t_ordersetting表中的已预约人数
     */
    void updateReservationsByOrderDate(Map<String,Object> paramMap);
}
