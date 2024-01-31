package model;
import enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static final int MAX_CAPACITY = 200;
    private Map<VehicleType, List<ParkingSpot>> parkingSpots;

    public ParkingLot() {
        parkingSpots = new HashMap<>();

        for (VehicleType vehicleType : VehicleType.values()) {
            List<ParkingSpot> spots = new ArrayList<>();
            for (int i = 0; i < MAX_CAPACITY / VehicleType.values().length; i++) {
                spots.add(new ParkingSpot());
            }
            parkingSpots.put(vehicleType, spots);
        }
    }

    public void displayAvailableSpots() {
        for (VehicleType vehicleType : VehicleType.values()) {
            System.out.println(vehicleType + " Spots Available " + getAvailableSpotCount(vehicleType));
        }
    }

    public void parkVehicle(VehicleType vehicleType) {
        List<ParkingSpot> spots = getAvailableSpots(vehicleType);
        if (spots.isEmpty()) {
            System.out.println("There is no available spot for " + vehicleType);
            return;
        }
        spots.get(0).occupy();
        System.out.println(vehicleType + " parked successfully");
    }

    public void retrieveVehicle(VehicleType vehicleType) {
        List<ParkingSpot> spots = getOccupiedSpots(vehicleType);
        if (spots.isEmpty()) {
            System.out.println("No " + vehicleType + " found in the parking lot");
            return;
        }
        spots.get(0).vacate();
        System.out.println(vehicleType + " retrieved successfully");
    }

    private int getAvailableSpotCount(VehicleType vehicleType) {
        int count = 0;
        for (ParkingSpot spot : parkingSpots.get(vehicleType)) {
            if (!spot.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    private List<ParkingSpot> getAvailableSpots(VehicleType vehicleType) {
        List<ParkingSpot> spots = parkingSpots.get(vehicleType);
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                availableSpots.add(spot);
                return availableSpots;
            }
        }
        return availableSpots;
    }

    private List<ParkingSpot> getOccupiedSpots(VehicleType vehicleType) {
        List<ParkingSpot> spots = parkingSpots.get(vehicleType);
        List<ParkingSpot> occupiedSpots = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied()) {
                occupiedSpots.add(spot);
                return occupiedSpots;
            }
        }
        return occupiedSpots;
    }
}