package com.company;

class DeliveryExecutive{
    String name;
    int tripCount;
    int charge;
    int currOrders;

    DeliveryExecutive(String name)
    {
        this.name = name;
        this.tripCount = 0;
        this.charge = 0;
        this.currOrders = 0;
    }

    public int getCurrOrders() {

        return currOrders;
    }

    public void setCurrOrders(int currOrders) {

        this.currOrders = currOrders;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getTripCount() {
        return tripCount;
    }

    public void setTripCount(int tripCount) {

        this.tripCount = tripCount;
    }

    public int getCharge() {

        return charge;
    }

    public void setCharge(int charge) {

        this.charge = charge;
    }

    @Override
    public String toString() {

        return name+" "+"allowance : "+ tripCount*10 +" charge : "+charge;
    }
}
