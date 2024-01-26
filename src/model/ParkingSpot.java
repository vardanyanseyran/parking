package model;

public class ParkingSpot {
    private boolean occupied;

    public ParkingSpot() {
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void vacate() {
        occupied = false;
    }
}
