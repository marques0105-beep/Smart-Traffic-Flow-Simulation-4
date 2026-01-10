package model.state;

import model.TrafficLight;

public class RedState implements LightState {

    private double timer = 0;

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;
        if (timer >= light.getStrategy().getRedDuration()) {
            light.setState(new GreenState());
        }
    }

    @Override
    public String getColor() {
        return "RED";
    }
}

