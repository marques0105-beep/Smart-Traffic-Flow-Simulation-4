package controller;

import model.Sensor;

public class AdaptiveCycle implements Strategy {

    private Sensor sensor;

    public AdaptiveCycle(Sensor sensor) {
        this.sensor = sensor;
    }

    public double getRedDuration() {
        return sensor.hasEmergency() ? 1 : 3;
    }

    public double getGreenDuration() {
        if (sensor.hasEmergency()) return 8;
        return 3 + sensor.getWaitingCars() * 0.5;
    }

    public double getYellowDuration() {
        return 1;
    }
}
