package model;

public class Sensor {

    private Road road;

    public Sensor(Road road) {
        this.road = road;
    }

    public boolean hasCarsWaiting() {
        return road.getQueueSize() > 0;
    }
}
