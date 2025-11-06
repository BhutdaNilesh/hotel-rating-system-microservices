package com.nb.user.service.external.services;


import com.nb.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating values);

}
