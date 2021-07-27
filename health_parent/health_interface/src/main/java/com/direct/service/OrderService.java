package com.direct.service;

import com.direct.entity.Result;

import java.util.Map;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:19 下午
 */
public interface OrderService {
    /**
     * 提交预约
     * @param map
     */
    Integer submit(Map map) throws Exception;

    /**
     * 根据id查询预约信息
     * @param id
     * @return
     */
    Map findById(Integer id);
}