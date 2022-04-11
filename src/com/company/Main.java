package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // to get the inputs from inputs.txt file given
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s+"/src/com/company/inputs.txt");

        //Scanning and using the inputs in the file
        Scanner sc = new Scanner(file);

        //number of Executives available
        int n = sc.nextInt(); // 5

        List<DeliveryExecutive> deliveryExecutives = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Trip> trips = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            deliveryExecutives.add(new DeliveryExecutive("DE" + i));
        }

        boolean flag = false;

        while (true) {
            if (flag)
                break;
//            1 -> Booking
//            2 -> Exit
//            3 -> Trip
            int val = sc.nextInt();
            switch (val) {
                case 1:
                    book(sc, deliveryExecutives, orders,trips);
                    break;
                case 2:
                    flag = true;
                    break;
                case 3:
                    System.out.println("Trip \t Executive \t Restaurant \t Destination \t Order \t PickUp \t DeliveryTime \t Delivery Chaege");
                    int count = 1;
                    for (Trip t : trips){
                        System.out.println(
                                count +"      \t"+t.de+"      \t"+t.source+"      \t"+t.destination+"      \t"+t.orders+"      \t"+t.pickUpTime+"      \t"+t.deliveryTime+"      \t"+t.deliveryCharge
                        );
                        count++;
                    }
                    System.out.println();
                default:
                    flag = true;
                    break;
            }
        }
        System.out.println("Executive\tAllowance\tDeliveryCharge\tTotal");
        for (DeliveryExecutive de : deliveryExecutives){
            if (de.tripCount!=0)
            System.out.println(de.getName() +"      \t"+ de.getTripCount()*10 +"       \t"+ de.getCharge() +"            \t"+ ((de.tripCount*10)+de.getCharge()));
        }
    }

    public static void book(Scanner sc, List<DeliveryExecutive> deliveryExecutives, List<Order> orders,List<Trip> trips) {
//        System.out.print("Customer Id : ");
        int customerId = sc.nextInt();
//        System.out.print("Restaurant : ");
        char source = sc.next().charAt(0);
//        System.out.print("Destination Point : ");
        char dest = sc.next().charAt(0);
        sc.nextLine();
//        System.out.print("Time : ");
        String time = sc.nextLine();
        System.out.println("Booking id : " + customerId +"\nAvailable Executives:");
        System.out.println("Executive\tDeliveryChargeEarned");
        for (DeliveryExecutive de:deliveryExecutives){
            System.out.println(de.getName()+"      \t"+de.getCharge());
        }
        Order order = new Order(source, dest, time);
        assignDeliveryExecutive(order,deliveryExecutives,orders,trips);
        orders.add(order);
        System.out.println();
    }

    public static void assignDeliveryExecutive(
            Order order,
            List<DeliveryExecutive> deliveryExecutives,
            List<Order> orders,
            List<Trip> trips
    ) {

        for (Order prevOrders : orders) {
            if (prevOrders.destination == order.destination && calculateTimeDifference(prevOrders.pickUpTime, order.pickUpTime) <= 15) {
                DeliveryExecutive deliveryExecutive = prevOrders.getDe();
                if(deliveryExecutive.getCurrOrders() < 5) {
                    order.setDe(prevOrders.getDe());
                    deliveryExecutive.setCharge(deliveryExecutive.getCharge()+5);
                    System.out.println("Allotted Delivery Executive: " + deliveryExecutive.getName());
                    return;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(DeliveryExecutive deliveryExecutive : deliveryExecutives){
            if(deliveryExecutive.getCharge() < min)
                min = deliveryExecutive.getCharge();
        }
        for(DeliveryExecutive deliveryExecutive : deliveryExecutives){
            if(deliveryExecutive.getCharge() == min)
            {
                order.setDe(deliveryExecutive);
                deliveryExecutive.setTripCount(deliveryExecutive.getTripCount()+1);
                deliveryExecutive.setCharge(deliveryExecutive.getCharge()+50);
                System.out.println("Allotted Delivery Executive: " + deliveryExecutive.getName());
                Trip trip = new Trip(
                        order.getDe().getName(),
                        order.getSource(),
                        order.getDestination(),
                        1,
                        "-",
                        "-", // -----------------------------------------
                        50
                );
                trips.add(trip);
                break;
            }
        }

    }

    public static int calculateTimeDifference(String t1, String t2) {
        // 9.00 AM
        String x1[] = t1.split(" ");
        String y1[] = x1[0].split("\\.");
        int h1 = Integer.parseInt(y1[0]);
        int m1 = Integer.parseInt(y1[1]);
        if (x1[1].equals("PM") && h1 != 12){
            h1+=12;
        }

        String x2[] = t2.split(" ");
        String y2[] = x2[0].split("\\.");
        int h2 = Integer.parseInt(y2[0]);
        int m2 = Integer.parseInt(y2[1]);
        if (x2[1].equals("PM") && h2 != 12){
            h2+=12;
        }
        int minDiff = 0;
        if (m1>m2){
            h2--;
            minDiff = 60 - (m1-m2);
        }
        minDiff = minDiff + (h2-h1)*60;
        return minDiff;
    }


}