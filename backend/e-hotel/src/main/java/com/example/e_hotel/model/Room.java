package com.example.e_hotel.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="roomid",nullable = false)
    private Integer roomId;

    @Column(name ="hotelid",nullable = false)
    private Integer hotelId;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int capacity;

    @Column
    private String amenities; // e.g. "WiFi, TV, Mini-bar"

    @Column(name ="seaview")
    private boolean seaView;

    @Column(name ="mountainview")
    private boolean mountainView;

    @Column(name ="isextendable")
    private boolean isExtendable;

    @Column
    private String problems; // e.g. "Leaky faucet"

    // Constructors
    public Room() {}

    public Room(Integer hotelId, double price, int capacity, String amenities,
                boolean seaView, boolean mountainView, boolean isExtendable, String problems) {
        this.hotelId = hotelId;
        this.price = price;
        this.capacity = capacity;
        this.amenities = amenities;
        this.seaView = seaView;
        this.mountainView = mountainView;
        this.isExtendable = isExtendable;
        this.problems = problems;
    }

    // Getters and Setters

    public Integer getRoomId() {
        return roomId;
    }
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getHotelId() {
        return hotelId;
    }
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAmenities() {
        return amenities;
    }
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public boolean isSeaView() {
        return seaView;
    }
    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public boolean isMountainView() {
        return mountainView;
    }
    public void setMountainView(boolean mountainView) {
        this.mountainView = mountainView;
    }

    public boolean isExtendable() {
        return isExtendable;
    }
    public void setExtendable(boolean extendable) {
        isExtendable = extendable;
    }

    public String getProblems() {
        return problems;
    }
    public void setProblems(String problems) {
        this.problems = problems;
    }

    //Return views
    @Transient
    private String view;

    public String getView() {
        List<String> views = new ArrayList<>();
        if (this.seaView) views.add("Sea");
        if (this.mountainView) views.add("Mountain");
        return views.isEmpty() ? "None" : String.join(", ", views);
    }

}
