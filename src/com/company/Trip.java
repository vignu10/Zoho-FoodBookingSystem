package com.company;

public class Trip {
    String de;
    char source;
    char destination;
    int orders;
    String pickUpTime;
    String deliveryTime;
    int deliveryCharge;

    public Trip(String de, char source, char destination, int orders, String pickUpTime, String deliveryTime, int deliveryCharge) {
        this.de = de;
        this.source = source;
        this.destination = destination;
        this.orders = orders;
        this.pickUpTime = pickUpTime;
        this.deliveryTime = deliveryTime;
        this.deliveryCharge = deliveryCharge;
    }
}