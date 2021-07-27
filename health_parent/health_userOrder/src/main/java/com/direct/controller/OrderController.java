package com.direct.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.direct.constant.MessageConstant;
import com.direct.entity.Result;
import com.direct.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：bfury
 * @date ：23/7/2021 2:35 下午
 */
@RestController
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        try {
            map.put("orderType", "微信预约");
            map.put("orderStatus", "未到诊");
            //调用servic中的方法预约
            Integer orderId = orderService.submit(map);
            return new Result(true, MessageConstant.ORDER_SUCCESS, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
