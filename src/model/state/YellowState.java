package model.state;

import model.TrafficLight;

public class YellowState implements LightState {

    private double timer = 0;

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;
        if (timer >= light.getStrategy().getYellowDuration()) {
            light.setState(new RedState());
        }
    }

    @Override
    public String getColor() {
        return "YELLOW";
    }
}


