package model;

import enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static final int MAX_CAPACITY = 200;
    private ParkingSpot[] compactSpots;
    private ParkingSpot[] largeSpots;

    private int compactSpotCount;
    private int largeSpotCount;

    public ParkingLot() {
        compactSpots = new ParkingSpot[MAX_CAPACITY / 2];
        largeSpots = new ParkingSpot[MAX_CAPACITY / 2];

        for (int i = 0; i < MAX_CAPACITY / 2; i++) {
            compactSpots[i] = new ParkingSpot();
            largeSpots[i] = new ParkingSpot();
        }
        compactSpotCount = MAX_CAPACITY / 2;
        largeSpotCount = MAX_CAPACITY / 2;
    }

    public void displayAvailableSpots() {
        System.out.println("Compact spots available " + compactSpotCount);
        System.out.println("large spots available " + largeSpotCount);
    }

    public void parkVehicle(VehicleType vehicleType) {
        ParkingSpot[] spots = getAvailableSpot(vehicleType);
        if (spots == null || spots.length == 0) {
            System.out.println("There is no available spots for " + vehicleType);
            return;
        }
        spots[0].occupy();
        updateSpotCount(vehicleType, -1);
        System.out.println(vehicleType + " parked successfully");
    }

    public void retrieveVehicle(VehicleType vehicleType) {
        ParkingSpot[] spots = getOccupiedSpots(vehicleType);
        if (spots.length == 0) {
            System.out.println(vehicleType + " is not found in the parking lot");
            return;
        }
        spots[0].vacate();
        updateSpotCount(vehicleType, 1);
        System.out.println(vehicleType + " retrieved successfully");
    }

    private void updateSpotCount(VehicleType vehicleType, int change) {
        if (vehicleType == VehicleType.CAR || vehicleType == VehicleType.MOTORCYCLE) {
            compactSpotCount += change;
        } else if (vehicleType == VehicleType.TRUCK) {
            largeSpotCount += change;
        }
    }

    private ParkingSpot[] getAvailableSpot(VehicleType vehicleType) {
        if (vehicleType == VehicleType.CAR || vehicleType == VehicleType.MOTORCYCLE) {
            return getAvailableSpot(compactSpots);
        } else if (vehicleType == VehicleType.TRUCK) {
            return getAvailableSpot(largeSpots);
        }
        return null;
    }

    private ParkingSpot[] getAvailableSpot(ParkingSpot[] spots) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                return new ParkingSpot[]{spot};
            }
        }
        return null;
    }

    private ParkingSpot[] getOccupiedSpots(VehicleType vehicleType) {
        ParkingSpot[] spots = (vehicleType == VehicleType.CAR || vehicleType == VehicleType.MOTORCYCLE) ? compactSpots : largeSpots;

        List<ParkingSpot> occupiedSpots = new ArrayList<>();

        for (ParkingSpot spot : spots) {
            if (spot.isOccupied()) {
                occupiedSpots.add(spot);
            }
        }
        return occupiedSpots.toArray(new ParkingSpot[0]);
    }
}
