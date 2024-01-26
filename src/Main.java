import enums.VehicleType;
import model.ParkingLot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display available spots");
            System.out.println("2. Park Vehicle");
            System.out.println("3. Retrieve Vehicle");
            System.out.println("4. Exit");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    parkingLot.displayAvailableSpots();
                    break;
                case 2:
                    System.out.println("Enter vehicle type (CAR, TRUCK, MOTORCYCLE)");
                    String vehicleTypeStr = scanner.next().toUpperCase();
                    VehicleType vehicleType = VehicleType.valueOf(vehicleTypeStr);
                    parkingLot.parkVehicle(vehicleType);
                    break;
                case 3:
                    System.out.println("Enter vehicle type (CAR, TRUCK, MOTORCYCLE)");
                    vehicleTypeStr = scanner.next().toUpperCase();
                    vehicleType = VehicleType.valueOf(vehicleTypeStr);
                    parkingLot.retrieveVehicle(vehicleType);
                    break;
                case 4:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, enter a valid choice");
            }
        }
    }
}