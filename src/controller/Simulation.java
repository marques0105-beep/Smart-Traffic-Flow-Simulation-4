package controller;

import model.Road;
import model.TrafficLight;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Road> roads = new ArrayList<>();
    private List<TrafficLight> lights = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addRoad(Road r) {
        roads.add(r);
    }

    public void addLight(TrafficLight l) {
        lights.add(l);
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public List<Road> getRoads() {
        return roads;
    }

    public List<TrafficLight> getLights() {
        return lights;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void update(double deltaTime) {
        for (TrafficLight light : lights) {
            light.update(deltaTime);
        }
        for (Vehicle v : vehicles) {
            v.update(deltaTime);
        }
    }
}
