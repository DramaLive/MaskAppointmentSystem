package com.dgut.service.impl;

import com.dgut.dao.UserMapper;
import com.dgut.domain.User;
import com.dgut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(String id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Override
    public void addNewUser(User user) throws Exception {
        userMapper.addNewUser(user);
    }
}
