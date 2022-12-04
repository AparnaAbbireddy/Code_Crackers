//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

import tools.Helper;

public class Ticket {
    int id = Helper.getId();
    public Passenger passenger;
    public Flight flight;

    public Flight getFlight() {
        return this.flight;
    }


    public Ticket(Passenger passenger, Flight flight) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public void displayDetails() {
        System.out.println("                                                -------------------------------------");
        System.out.println("                                                   Flight details: ");
        System.out.println("                                                -------------------------------------");
        this.flight.DisplayFlightDetailInTicket();
        System.out.println("                                                -------------------------------------");
        System.out.println("                                                   Passenger details: ");
        System.out.println("                                                -------------------------------------");
        this.passenger.DisplayPassengerDetails();
    }


}
