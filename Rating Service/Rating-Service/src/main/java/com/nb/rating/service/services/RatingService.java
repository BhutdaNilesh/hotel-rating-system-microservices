package com.nb.rating.service.services;

import com.nb.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    // get all by userID
    List<Rating> getRatingByUserId(String userId);

    // get all by Hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
