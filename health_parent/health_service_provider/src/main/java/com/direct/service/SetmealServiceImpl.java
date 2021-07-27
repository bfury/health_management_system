package com.direct.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.direct.constant.RedisConstant;
import com.direct.dao.SetmealDao;
import com.direct.entity.PageResult;
import com.direct.entity.QueryPageBean;
import com.direct.pojo.Setmeal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：20/7/2021 9:44 上午
 */

//@Service
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private  JedisPool jedisPool;


    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        //设置套餐跟检查组的关系表
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());

    }

    private void savePic2Redis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //分页助手插件
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Setmeal> result = page.getResult();
        return new PageResult(total, result);
    }

    @Override
    public void deleteById(Integer id) {
        //根据套餐id删除中间表数据（清理原有关联关系）
        setmealDao.deleteAssociation(id);
        setmealDao.deleteById(id);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(Integer id) {
        return setmealDao.findCheckGroupIdsBySetmealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        //根据套餐id删除中间表数据（清理原有关联关系）
        setmealDao.deleteAssociation(setmeal.getId());
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        //更新套餐基本信息
        setmealDao.edit(setmeal);
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    private void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmealId", setmealId);
                map.put("checkGroupId", checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}