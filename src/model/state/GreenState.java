package model.state;

import model.TrafficLight;

public class GreenState implements LightState {

    private double timer = 0;

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;
        if (timer >= light.getStrategy().getGreenDuration()) {
            light.setState(new YellowState());
        }
    }

    @Override
    public String getColor() {
        return "GREEN";
    }
}


