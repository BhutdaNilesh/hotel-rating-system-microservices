package com.nb.user.service;

import com.nb.user.service.entities.Rating;
import com.nb.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}


	// All below is done to test create Rating using Feign client
	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("Using feign client").build();

		ratingService.createRating(rating);
		System.out.println("Rating created using Feign Client");
	}

}
