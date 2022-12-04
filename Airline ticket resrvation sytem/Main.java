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
                    "-------------------------------------------------- 2. Exit and Save ----------------------------------------------");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Please enter the serial number for required operation from the list of operations:");
            choice = Helper.scan.nextByte();
            switch (choice) {
                case 1:
                    controller.displayFlightsDetails();
                    break;
           
                case 2:
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
