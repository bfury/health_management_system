package com.direct.dao;
import com.direct.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    public void deleteById(Integer id);

    public long findCountByCheckItemId(Integer checkItemId);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);

    public List<CheckItem> findAll();

    public List<CheckItem> findCheckItemById(Integer id);
}