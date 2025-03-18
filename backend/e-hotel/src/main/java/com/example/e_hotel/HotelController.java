package com.example.e_hotel;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")  
public class HotelController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Welcome Souhail to the Hotel Booking API!";
    }
}
