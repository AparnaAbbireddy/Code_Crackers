package controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import models.Flight;
import models.Passenger;
import models.Ticket;
import tools.Helper;
import tools.Validation;
import java.util.InputMismatchException;

public class BookingController{
    ArrayList<Flight> flightList = new ArrayList<Flight>();
    ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

    public void addFlight() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter the flight's Code:");
        Helper.scan.nextLine();
        String flightNo = Helper.scan.nextLine().toUpperCase().trim();

        System.out.println("Enter the capacity of the flight ");
        int capacity = Helper.scan.nextInt();

        System.out.println("Enter from where the flight will go ");
        Helper.scan.nextLine();
        String from = Helper.scan.nextLine().toLowerCase().trim();

        System.out.println("Enter from where the flight will go in airport code");
        String fromCode = Helper.scan.nextLine().toUpperCase().trim();

        System.out.println("Enter to where the flight will go");
        String to = Helper.scan.nextLine().toLowerCase().trim();

        System.out.println("Enter to where the flight will go in airport code");
        String toCode = Helper.scan.nextLine().toUpperCase().trim();

        System.out.println("Enter the timing of arrival in format: yyyy-MM-dd HH:mm");
        String ArrivalScanner = Helper.scan.nextLine().trim();
        LocalDateTime arrival = Helper.StringToDateTimeFormatter(Validation.validateDateTime(ArrivalScanner));

        System.out.println("Enter the timing of arrival in format: yyyy-MM-dd HH:mm");
        String departureScanner = Helper.scan.nextLine().trim();
        LocalDateTime departure = Helper.StringToDateTimeFormatter(Validation.validateDateTime(departureScanner));

        System.out.println("Enter the price of the ticket");
        var price = Helper.scan.nextDouble();

        flightList.add(new Flight(flightNo, capacity, from, fromCode, to, toCode, arrival, departure, price));

    }

    public void displayFlightsDetails() {
        int index = 1;
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        for (Flight flight : flightList) {
            System.out.println("                                                 " + index + "-");
            flight.DisplayFlightDetail();
            index++;
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private Passenger addPassenger() {
        System.out.println("Enter the Passenger Name :");
        Helper.scan.nextLine();
        String fullName = Helper.scan.nextLine();

        System.out.println("Enter the Passenger's passport :");
        String passportNo = Helper.scan.nextLine();

        System.out.println("Enter the passenger's age");
        int age = Helper.scan.nextInt();

        System.out.println("Enter Passenger's nationality ");
        Helper.scan.nextLine();
        String nationality = Helper.scan.nextLine();

        Passenger passenger = new Passenger(fullName, passportNo, age, nationality);
        return passenger;

    }

    public void bookTicket() {
        displayFlightsDetails();
        System.out.print("Enter the number of the flight that you want to book:");
        int index = Helper.scan.nextInt();
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        if (!flightList.get(index - 1).bookFlight()) {
            System.out.println("No seat is available please select different flight!!!");
            return;
        }
        System.out.println("Enter the details of the passenger:\n");
        var passenger = addPassenger();
        ticketList.add(new Ticket(passenger, flightList.get(index - 1)));

        System.out.println("Do you want to print the ticket? (please Type yes/y if you want to print or no/n if not)");
        String input = Helper.scan.nextLine().toLowerCase().trim();
        if (input.equals("yes") || input.equals("y")) {
            ticketList.get(ticketList.size() - 1).printTicketToPdf();
        }

    }

    public void displayTickets() {
        int index = 1;
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        for (Ticket ticket : ticketList) {
            System.out.println("                                                  " + index + "-");
            ticket.displayDetails();
            index++;
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void cancelTicket() {
        int index;
        displayTickets();
        System.out.print("Enter the number of the ticket that you want to cancel it:");
        index = Helper.scan.nextInt();
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        var d = getFlightIndexById(ticketList.get(index - 1).flight.getId());
        var deleted = ticketList.remove(index - 1);
        System.out.println("                                                   the deleted ticket:");
        deleted.displayDetails();
        flightList.get(d).cancelFlight();
    }

    private int getFlightIndexById(int input) {
        int index = -1;
        for (Flight flight : flightList) {
            var id = flight.getId();

            if (id == input) {
                index = flightList.indexOf(flight);

            }
        }
        return index;
    }

    public void searchForFlight() throws ParseException {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter from where that flight you want to book:");
        Helper.scan.nextLine();
        String from = Helper.scan.nextLine();

        System.out.println("Enter to where that flight you want to book:");
        String to = Helper.scan.nextLine();

        System.out.println("Enter the timing of flight in format: yyyy-MM-dd");
        String time = Helper.scan.nextLine().trim();
        LocalDate date = Helper.StringToDateFormatter(Validation.validateDate(time));

        Boolean flag = true;

        for (Flight flight : flightList) {


            if ((flight.getFrom().equals(from.toLowerCase().trim()) && flight.getTo().equals(to.toLowerCase().trim())
                    && flight.getDeparture().toLocalDate().equals(date))
                    || (flight.getFromCode().equals(from.toUpperCase().trim())
                            && flight.getToCode().equals(to.toUpperCase().trim())
                            && flight.getDeparture().toLocalDate().equals(date))
                    || (flight.getFromCode().equals(from.toUpperCase().trim())
                            && flight.getTo().equals(to.toLowerCase().trim())
                            && flight.getDeparture().toLocalDate().equals(date))
                    || (flight.getFrom().equals(from.toLowerCase().trim())
                            && flight.getToCode().equals(to.toUpperCase().trim())
                            && flight.getDeparture().toLocalDate().equals(date))) {
                flight.DisplayFlightDetail();

                System.out.println(
                        "                                                -------------------------------------");
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Not Found!!!");
        }

    }

    public void printTicket() {
        displayTickets();
        System.out.print("Enter the number of the ticket:");
        int index = Helper.scan.nextInt();
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        var d = getFlightIndexById(ticketList.get(index - 1).flight.getId());
        ticketList.get(d).printTicketToPdf();

    }

    public static int vi(int input) {

        try {

        } catch (Exception iException) {
            // TODO: handle exception
        }
        // if (input == (int) input) {
        // return input;
        // } else {
        // System.out.println("plz enter integer");
        // int i= Helper.scan.nextInt();
        // vi(i);
        // }
        return 6;
    }
}
