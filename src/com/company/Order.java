package com.company;

public class Order {
    char source;
    char destination;
    String pickUpTime;
    DeliveryExecutive de;

    Order(char source,char destination,String pickUpTime){
        this.source = source;
        this.destination = destination;
        this.pickUpTime = pickUpTime;
    }

    public char getSource() {
        return source;
    }

    public void setSource(char source) {
        this.source = source;
    }

    public char getDestination() {
        return destination;
    }

    public void setDestination(char destination) {
        this.destination = destination;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public DeliveryExecutive getDe() {
        return de;
    }

    public void setDe(DeliveryExecutive de) {
        this.de = de;
    }

    @Override
    public String toString() {
        return "{" + source+"->"+destination +" " + pickUpTime +"}";
    }

}
