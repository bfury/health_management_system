package com.direct.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.direct.constant.MessageConstant;
import com.direct.dao.OrderDao;
import com.direct.dao.OrderSettingDao;
import com.direct.entity.Result;
import com.direct.pojo.Order;
import com.direct.pojo.OrderSetting;
import com.direct.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：bfury
 * @date ：23/7/2021 2:41 下午
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 提交预约
     *
     * @param map
     */
    @Override
    public Integer submit(Map map) throws Exception {
        Integer setmealId = Integer.parseInt(map.get("setmealId").toString());
        //2. 校验用户选择的日期是否可以预约(t_ordersetting)
        Date date = DateTimeUtil.getDateByString(map.get("orderDate").toString(), "yyyy-MM-dd");
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (null == orderSetting) {
            //对应的日期是否设置过预约设置信息
            throw new Exception(MessageConstant.ORDER_FULL);
        }
        if (orderSetting.getNumber() <= orderSetting.getReservations()) {
            // + 对应的日期是否已经约满
            throw new Exception(MessageConstant.ORDER_FULL);
        }

        //3. 校验用户是否重复预约（同一个手机号在同一天内对同一个套餐）
        Order temp = new Order();
        temp.setMemberId(84);
        temp.setOrderDate(date);
        temp.setSetmealId(setmealId);
        Order order = orderDao.findByMemberIdAndOrderDateAndSetmealId(temp);
        //+ 向t_order表插入一条预约数据
        temp.setOrderType(map.get("orderType").toString());
        temp.setOrderStatus(map.get("orderStatus").toString());
        orderDao.add(temp);
        // 按照日期更新t_ordersetting表中的已预约人数
        Map<String, Object> paramMap = new HashMap<String, Object>(2);
        paramMap.put("date", date);
        paramMap.put("reservations", orderSetting.getReservations() + 1);
        orderSettingDao.updateReservationsByOrderDate(paramMap);
        return temp.getId();
    }

    /**
     * 根据id查询预约信息
     *
     * @param id
     * @return
     */
    @Override
    public Map findById(Integer id) {
        return orderDao.findById(id);
    }
}