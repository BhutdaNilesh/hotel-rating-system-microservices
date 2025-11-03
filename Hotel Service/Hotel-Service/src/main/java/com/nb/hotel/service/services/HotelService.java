package com.nb.hotel.service.services;

import com.nb.hotel.service.entities.Hotel;
import com.nb.hotel.service.repositories.HotelRepository;

import java.util.List;

public interface HotelService {

    // create
    Hotel create(Hotel hotel);

    // get all
    List<Hotel> getAll();

    // get by id
    Hotel get(String id);
}
