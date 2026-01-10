package model;

public class Sensor {

    private Road road;

    public Sensor(Road road) {
        this.road = road;
    }

    public int getWaitingCars() {
        return road.getQueueSize();
    }

    public boolean hasEmergency() {
        return road.hasEmergencyVehicle();
    }
}
