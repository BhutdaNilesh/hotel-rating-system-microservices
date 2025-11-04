package com.nb.user.service.services.impl;

import com.nb.user.service.entities.User;
import com.nb.user.service.exceptions.ResourceNotFoundException;
import com.nb.user.service.repositories.UserRepository;
import com.nb.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

//    private Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

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
        // getting user from Database using userRepository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! : " + userId));

        // fetch the ratings data from RATING_SERVICE using restTempate
        //localhost:8083/ratings/users/7effbc94-5b8c-4a90-a966-f39cbc27068b

        ArrayList ratingsByUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserID(), ArrayList.class);
        user.setRatings(ratingsByUser);
        return user;
    }
}
