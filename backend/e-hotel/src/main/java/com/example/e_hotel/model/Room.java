package com.example.e_hotel;

import jakarta.persistence.*;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;
    private int hotelID;
    private double price;
    private int capacity;
    private String view;
    private boolean extendable;
    private String damages;

    public int getRoomID(){
        return roomID;
    }
    public void setRoomID(int roomID){
        this.roomID=roomID;
    }
    public int getHotelID(){
        return hotelID;
    }
    public void setHotelID(int hotelID){
        this.hotelID=hotelID;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }
    public String getView(){
        return view; 
    }
    public void setView(String view){
        this.view = view; 
    }
    public boolean isExtendable(){
        return extendable;
    }
    public void setExtendable(boolean extendable){
        this.extendable = extendable; 
    }
    public String getDamages(){
        return damages; 
    }
    public void setDamages(String damages){
        this.damages = damages; 
    }
}
