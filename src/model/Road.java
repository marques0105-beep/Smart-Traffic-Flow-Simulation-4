package model;

import java.util.LinkedList;
import java.util.Queue;

public class Road {

    private Queue<Vehicle> queue = new LinkedList<>();
    private TrafficLight trafficLight;

    public Road(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public void addVehicle(Vehicle v) {
        queue.add(v);
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean hasEmergencyVehicle() {
        return queue.stream().anyMatch(Vehicle::isEmergency);
    }

    public boolean hasRedLight() {
        return trafficLight.isRed();
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }
}

