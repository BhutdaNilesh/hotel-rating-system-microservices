package com.nb.user.service.services;

import com.nb.user.service.entities.User;

import java.util.List;

public interface UserService {

    // create
    User saveUser(User user);

    //get All Users
    List<User> getAllUser();

    // user by id
    User getUser(String userId);

    // TODO: delete and update

}
