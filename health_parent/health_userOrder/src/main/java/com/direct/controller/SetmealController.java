package com.direct.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.direct.constant.MessageConstant;
import com.direct.entity.Result;
import com.direct.pojo.Setmeal;
import com.direct.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author ：bfury
 * @date ：23/7/2021 4:09 下午
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
//    @Autowired
//    private JedisPool jedisPool;

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getSetmeal")
    public Result getSetmeal() {

        try {

                List<Setmeal> setmeals = setmealService.findAll();
                String setmealsJson = JSON.toJSONString(setmeals);
                return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeals);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmealService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}

