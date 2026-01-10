package model;

import controller.Strategy;
import model.state.LightState;
import model.state.RedState;

public class TrafficLight {

    private String id;
    private boolean red;

    private LightState state;
    private Strategy strategy;

    public TrafficLight(String id, Strategy strategy) {
        this.id = id;
        this.strategy = strategy;
        this.state = new RedState();
        this.state.enter(this);
    }

    public void update(double deltaTime) {
        state.update(this, deltaTime);
    }

    public void setState(LightState newState) {
        this.state = newState;
        newState.enter(this);
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean r) {
        this.red = r;
    }

    // “É permitido avançar?”
    public boolean isGreenAllowed() {
        // Vermelho = proibido; amarelo + verde = permitido
        return !red;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public String getId() {
        return id;
    }

    public String getStateName() {
        return state.getName();
    }
}
