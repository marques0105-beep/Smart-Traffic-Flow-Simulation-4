package model;

import util.Metrics;

public class Vehicle {

    private double position = 0;
    private double speed;
    private Road road;
    private boolean emergency;

    private double waitingTime = 0;
    private boolean waiting = false;

    public Vehicle(Road road, double speed, boolean emergency) {
        this.road = road;
        this.speed = speed;
        this.emergency = emergency;
    }

    public void update(double deltaTime, Metrics metrics) {
        if (!emergency && road.hasRedLight()) {
            waiting = true;
            waitingTime += deltaTime;
            return;
        }

        if (waiting) {
            metrics.addWaitingTime(waitingTime);
            metrics.incrementServedCars();
            waiting = false;
            waitingTime = 0;
        }

        position += speed * deltaTime;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public double getPosition() {
        return position;
    }

    public void reset() {
        position = 0;
        waiting = false;
        waitingTime = 0;
    }
}
