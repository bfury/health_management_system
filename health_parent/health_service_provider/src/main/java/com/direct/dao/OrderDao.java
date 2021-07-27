package com.direct.dao;

import com.direct.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:21 下午
 */
public interface OrderDao {
    /**
     * 根据会员id、预约日期、套餐id查询预约信息
     * @param temp
     * @return
     */
    Order findByMemberIdAndOrderDateAndSetmealId(Order temp);

    /**
     * 保存预约信息
     * @param temp
     */
    void add(Order temp);

    /**
     * 根据id查询预约信息
     * @param id
     * @return
     */
    Map findById(Integer id);

    /**
     * 查询对应数日的预约数
     * @param reportDate
     * @return
     */
    Long findCountByEqualDate(String reportDate);
    /**
     * 查询对应数日的到诊数
     * @param reportDate
     * @return
     */
    Long findVisitedCountByEqualDate(String reportDate);

    /**
     * 查询大于等于对应数日的预约数
     * @param reportDate4ThisWeek
     * @return
     */
    Long findCountByGreateAndEqualDate(String reportDate4ThisWeek);

    /**
     * 查询大于等于对应数日的到诊数
     * @param reportDate4ThisWeek
     * @return
     */
    Long findVisitedCountByGreaterAndEqualDate(String reportDate4ThisWeek);
}
