package com.nb.user.service.services.impl;

import com.nb.user.service.entities.Hotel;
import com.nb.user.service.entities.Rating;
import com.nb.user.service.entities.User;
import com.nb.user.service.exceptions.ResourceNotFoundException;
import com.nb.user.service.external.services.HotelService;
import com.nb.user.service.repositories.UserRepository;
import com.nb.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

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

        Rating[] ratingsByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserID(), Rating[].class);

        List<Rating> ratingList = Arrays.stream(ratingsByUser).toList();
        List<Rating> ratings = ratingList.stream().map(rating -> {
            // api call to Hotel Service to get the Hotel
            // http://localhost:8082/hotels/dea5de24-f018-4e8d-b0ba-a9235bcd86b2
            // Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // set the Hotel Details to Rating
            rating.setHotel(hotel);
            // return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratings);
        return user;
    }
}
