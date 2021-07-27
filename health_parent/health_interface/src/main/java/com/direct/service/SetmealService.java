package com.direct.service;

import com.direct.entity.PageResult;
import com.direct.entity.QueryPageBean;
import com.direct.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：20/7/2021 9:32 上午
 */
public interface SetmealService {

    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    public Setmeal findById(Integer id);

    public List<Integer> findCheckGroupIdsBySetmealId(Integer id);

    public void edit(Setmeal setmeal, Integer[] checkgroupIds);

    public List<Setmeal> findAll();

}