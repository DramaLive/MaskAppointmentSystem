package com.dgut.dao;

import com.dgut.domain.Mask;
import com.dgut.domain.PagingVO;

import java.util.List;

public interface MaskMapper {

    public Integer maskCount();

    public List<Mask> findByPaging(PagingVO pagingVO) throws Exception;

    public void add(Mask mask) throws Exception;

    public void updateById(Mask mask) throws Exception;

    public Mask findById(Integer id) throws Exception;

    public void removeById(Integer id) throws Exception;

    public List<Mask> findByName(String name) throws Exception;

    public List<Mask> nameList() throws Exception;
}
