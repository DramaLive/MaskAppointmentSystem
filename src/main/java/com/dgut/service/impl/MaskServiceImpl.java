package com.dgut.service.impl;

import com.dgut.dao.MaskMapper;
import com.dgut.domain.Mask;
import com.dgut.domain.PagingVO;
import com.dgut.service.MaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaskServiceImpl implements MaskService {
    @Autowired
    private MaskMapper maskMapper;

    @Override
    public Integer maskCount() {
        return maskMapper.maskCount();
    }

    @Override
    public List<Mask> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Mask> list = maskMapper.findByPaging(pagingVO);
        return list;
    }

    @Override
    public void add(Mask mask) throws Exception {
        maskMapper.add(mask);
    }

    @Override
    public Mask findById(Integer id) throws Exception {
        return maskMapper.findById(id);
    }

    @Override
    public void updateById(Mask mask) throws Exception {
        maskMapper.updateById(mask);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        maskMapper.removeById(id);
    }

    @Override
    public List<Mask> findByName(String name) throws Exception {
        return maskMapper.findByName(name);
    }

    @Override
    public List<Mask> nameList() throws Exception {
        return maskMapper.nameList();
    }

}
