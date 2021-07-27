package com.direct.service;

import com.direct.entity.PageResult;
import com.direct.entity.QueryPageBean;
import com.direct.pojo.CheckGroup;

import java.util.List;
/**
 * @author     ：bfury
 * @date       ：2021-7-20
 */
public interface CheckGroupService {

    public void add(CheckGroup checkGroup, Integer[] checkItemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkItemIds);

    public void deleteById(Integer id);

    public List<CheckGroup> findAll();

}
