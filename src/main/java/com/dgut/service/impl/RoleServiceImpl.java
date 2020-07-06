package com.dgut.service.impl;

import com.dgut.dao.RoleMapper;
import com.dgut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String findNameById(Integer roleId) throws Exception {
        return roleMapper.findNameById(roleId);
    }
}
