package controller;

import model.Road;

public class AdaptiveCycle implements Strategy {

    private Road road;

    public AdaptiveCycle(Road road) {
        this.road = road;
    }

    @Override
    public double getRedDuration() {
        return 3 + road.getQueueSize() * 0.5;
    }

    @Override
    public double getGreenDuration() {
        return 3 + road.getQueueSize() * 0.3;
    }

    @Override
    public double getYellowDuration() {
        return 2;
    }
}
