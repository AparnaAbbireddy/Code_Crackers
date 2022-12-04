import com.google.gson.Gson;
import controllers.BookingController;
import tools.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Byte choice;
        BookingController controller = Storage.loadData();
        for (;;) {
            System.out.println("");
            System.out.println(
                    "************************* Hearty welcome to our flight ticket purchase system! ***********************************");
            System.out.println();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 1. Display List of Flights -------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 2. Search for a flight ----------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 3. Booking a flight Ticket ----------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 4. Display List of Tickets--------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 5. Printing a ticket  --------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 6. Ticket cancellation-------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 7. Exit and Save ----------------------------------------------");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Please enter the serial number for required operation from the list of operations:");
            choice = Helper.scan.nextByte();
            switch (choice) {
                case 1:
                    controller.displayFlightsDetails();
                    break;
                case 2:
                    controller.searchForFlight();
                    break;
                case 3:
                    controller.bookTicket();
                    break;
                case 4:
                    controller.displayTickets();
                    break;
                case 5:
                    controller.printTicket();
                    break;
                case 6:
                    controller.cancelTicket();
                    break;
                case 7:
                    Gson gson = new Gson();
                    String data = gson.toJson(controller);
                    if (Storage.savingData(data)) {
                        System.out.println("***Success***");

                        return;
                    } else {
                        System.out.println("Something went wrong !!! plz try again...");

                        break;
                    }
                default:
                    System.out.println("You've enter the wrong number!!!");
                    break;
            }

        }

    }

}
