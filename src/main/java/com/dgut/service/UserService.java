package com.dgut.service;

import com.dgut.domain.User;

public interface UserService {

    public User findUserById(String id) throws Exception;

    public void addNewUser(User user) throws Exception;
}
