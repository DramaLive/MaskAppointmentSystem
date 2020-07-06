package com.dgut.dao;

import com.dgut.domain.User;

public interface UserMapper {

    public User findUserById(String id);

    public void addNewUser(User user);
}
