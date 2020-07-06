package com.dgut.service;

import com.dgut.domain.Mask;

import java.util.List;

public interface MaskService {

    public Integer maskCount();

    List<Mask> findByPaging(Integer toPageNo) throws Exception;

    public void add(Mask mask) throws Exception;

    public Mask findById(Integer id) throws Exception;

    public void updateById(Mask mask) throws Exception;

    public void removeById(Integer id) throws Exception;

    public List<Mask> findByName(String name) throws Exception;

    public List<Mask> nameList() throws Exception;
}
