package com.direct.dao;

import com.direct.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author ：bfury
 * @date ：20/7/2021 9:43 上午
 */
public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    public Page<Setmeal> selectByCondition(String queryString);

    public void deleteAssociation(Integer id);

    public void deleteById(Integer id);

    public Setmeal findById(Integer id);

    public List<Integer> findCheckGroupIdsBySetmealId(Integer id);

    public void edit(Setmeal setmeal);

    public List<Setmeal> findAll();

}
