import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws Exception {
        Byte choice;
        BookingController controller = Storage.loadData();
        for (;;) {
            System.out.println("");
            System.out.println(
                    "####################################### Welcome to the flight ticket purchase system! ########################################");
            System.out.println();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 1. List All Flights -------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 2. Add Flight -------------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 3. Edit A Flight  ---------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 4. Delete Flight ----------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 5. Search For A flight ----------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 6. Book A Ticket ----------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 7. Edit A Ticket ----------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 8. List All Ticket --------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 9. Print A Ticket  --------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 10. Cancel A Ticket -------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- 11. Exit The System And Save ----------------------------------------------");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Please select the serial number to be operated:");
            choice = Helper.scan.nextByte();
            switch (choice) {
                case 1 -> controller.displayFlightsDetails();
                case 2 -> new AddFlight().addFlight();
                case 3 -> new EditFlight().editFlight();
                case 4 -> new DeletingFlight().deleteFlight();
                case 5 -> controller.searchForFlight();
                case 6 -> controller.bookTicket();
                case 7 -> controller.editTicket();
                case 8 -> controller.displayTickets();
                case 9 -> controller.printTicket();
                case 10 -> controller.cancelTicket();
                case 11 -> {
                    Gson gson = new Gson();
                    String data = gson.toJson(controller);
                    if (Storage.savingData(data)) {
                        System.out.println("Success...");

                        return;
                    } else {
                        System.out.println("Something went wrong !!! plz try again...");

                    }
                }
                default -> System.out.println("You've enter the wrong number!!!");
            }

        }

    }
}
