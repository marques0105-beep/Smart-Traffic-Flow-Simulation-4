package controller;

import model.*;
import util.Metrics;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private Road road;
    private List<Intersection> intersections;
    private AdaptiveCycle adaptiveStrategy;
    private Metrics metrics;

    public Simulation() {

        road = new Road(800);
        intersections = new ArrayList<>();
        adaptiveStrategy = new AdaptiveCycle();
        metrics = new Metrics();

        // duas interseções em posições diferentes
        intersections.add(new Intersection(
                new TrafficLight(adaptiveStrategy), 300
        ));
        intersections.add(new Intersection(
                new TrafficLight(adaptiveStrategy), 600
        ));

        road.addVehicle(new Vehicle(50));
    }

    public void update(double dt) {

        // SENSOR: contar carros parados antes de cada interseção
        adaptiveStrategy.setWaitingCars(countWaitingCars());

        // atualizar semáforos
        for (Intersection i : intersections) {
            i.update(dt);
        }

        // lógica dos veículos
        for (Vehicle v : road.getVehicles()) {

            boolean mustStop = false;

            for (Intersection i : intersections) {
                boolean nearIntersection =
                        v.getPosition() >= i.getPosition() - 40 &&
                                v.getPosition() <= i.getPosition();

                if (!i.getTrafficLight().isGreen() && nearIntersection) {
                    mustStop = true;
                }
            }

            if (mustStop) v.stop();
            else v.go();
        }

        road.update(dt);
    }

    public int countWaitingCars() {
        int count = 0;
        for (Vehicle v : road.getVehicles()) {
            for (Intersection i : intersections) {
                if (v.getPosition() >= i.getPosition() - 40 &&
                        v.getPosition() <= i.getPosition()) {
                    count++;
                }
            }
        }
        return count;
    }

    // ===== GETTERS =====
    public Road getRoad() {
        return road;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public Metrics getMetrics() {
        return metrics;
    }
}



