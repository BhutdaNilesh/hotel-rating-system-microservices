package com.nb.user.service.external.services;

import com.nb.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
@Service
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId")  String hotelId);
}
