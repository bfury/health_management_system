package com.direct.dao;

import com.direct.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author     ：bfury
 * @date       ：2021-7-20
 */
public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> selectByCondition(String queryString);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void deleteAssociation(Integer id);

    public void edit(CheckGroup checkGroup);

    public void deleteById(Integer id);

    public List<CheckGroup> findAll();

    public long findCountByCheckGroupId(Integer id);

    public List<CheckGroup> findCheckGroupById(Integer id);

}
