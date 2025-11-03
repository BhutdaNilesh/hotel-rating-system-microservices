package com.nb.user.service.services.impl;

import com.nb.user.service.entities.User;
import com.nb.user.service.exceptions.ResourceNotFoundException;
import com.nb.user.service.repositories.UserRepository;
import com.nb.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserID(randomUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server!! : "+userId));
    }
}
