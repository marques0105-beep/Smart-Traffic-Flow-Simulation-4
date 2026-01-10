package controller;

import model.*;
import util.Metrics;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<TrafficLight> lights = new ArrayList<>();
    private Metrics metrics = new Metrics();

    private boolean running = false;
    private double speedMultiplier = 1.0;

    public void tick(double deltaTime) {
        double adjusted = deltaTime * speedMultiplier;

        for (TrafficLight l : lights)
            l.update(adjusted);

        for (Vehicle v : vehicles)
            v.update(adjusted, metrics);
    }

    public void update(double deltaTime) {
        if (running) tick(deltaTime);
    }

    public void start() { running = true; }
    public void stop() { running = false; }

    public void reset() {
        vehicles.forEach(Vehicle::reset);
        lights.forEach(TrafficLight::reset);
        metrics.reset();
    }

    public void setSpeedMultiplier(double speed) {
        this.speedMultiplier = speed;
    }

    public void addVehicle(Vehicle v) { vehicles.add(v); }
    public void addLight(TrafficLight l) { lights.add(l); }

    public List<Vehicle> getVehicles() { return vehicles; }
    public List<TrafficLight> getLights() { return lights; }
    public Metrics getMetrics() { return metrics; }
}


