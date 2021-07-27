package com.direct.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.direct.dao.OrderSettingDao;
import com.direct.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:32 下午
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 批量导入预约数据
     *
     * @param orderSettings
     */
    @Override
    public void batchInsert(List<OrderSetting> orderSettings) {
        for (OrderSetting setting : orderSettings) {
            //校验对应的日期之前是否设置过预约数据
            Long count = orderSettingDao.findCountByOrderDate(setting.getOrderDate());
            //已设置过，则执行更新可预约人数
            if (count > 0) {
                orderSettingDao.updateNumberByOrderDate(setting);
                continue;
            }
            // 未设置过则可以直接保存
            orderSettingDao.add(setting);
        }

    }

    /**
     * 按照月份查询预约设置信息
     *
     * @param date 2020-02
     * @return
     */
    @Override
    public List<OrderSetting> getOrderSettingByMonth(String date) {
        return orderSettingDao.getOrderSettingByMonth(date + "-%");
    }

    /**
     * 设置预约信息
     *
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        System.out.println(orderSetting.getOrderDate());
        //1 根据日期查询之前是否设置过预约信息
        Long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        //1.1 未设置过，则直接新增
        if (count == 0) {
            orderSettingDao.add(orderSetting);
            return;
        }
        //1.2 已设置过，则按照日期更新可预约人数
        orderSettingDao.updateNumberByOrderDate(orderSetting);
    }
}
