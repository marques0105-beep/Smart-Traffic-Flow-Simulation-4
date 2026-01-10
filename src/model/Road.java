package model;

import java.util.ArrayList;
import java.util.List;

public class Road {

    private String name;
    private double length;

    private double startX;
    private double startY;

    // Posição ao longo da estrada onde está a interseção (meio da estrada)
    private double intersectionPosition;

    private TrafficLight trafficLight;

    // Fila de veículos (para estratégias adaptativas)
    private List<Vehicle> queue = new ArrayList<>();

    public Road(String name, double length) {
        this.name = name;
        this.length = length;
        this.intersectionPosition = length / 2.0;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public void setStart(double x, double y) {
        this.startX = x;
        this.startY = y;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getIntersectionPosition() {
        return intersectionPosition;
    }

    public void setIntersectionPosition(double intersectionPosition) {
        this.intersectionPosition = intersectionPosition;
    }

    public void setTrafficLight(TrafficLight light) {
        this.trafficLight = light;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    // ===== Fila de veículos =====

    public void addToQueue(Vehicle v) {
        if (!queue.contains(v)) {
            queue.add(v);
        }
    }

    public void removeFromQueue(Vehicle v) {
        queue.remove(v);
    }

    public int getQueueSize() {
        return queue.size();
    }

    public List<Vehicle> getQueue() {
        return queue;
    }
}
