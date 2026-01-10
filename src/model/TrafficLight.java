package model;

import controller.Strategy;
import model.state.LightState;
import model.state.RedState;

public class TrafficLight {

    private LightState state;
    private Strategy strategy;

    public TrafficLight(Strategy strategy) {
        this.strategy = strategy;
        this.state = new RedState();
    }

    public void update(double deltaTime) {
        state.update(this, deltaTime);
    }

    public void setState(LightState state) {
        this.state = state;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isRed() {
        return state.getColor().equals("RED");
    }

    public String getColor() {
        return state.getColor();
    }

    public void reset() {
        state = new RedState();
    }
}
