package model.state;

import model.TrafficLight;

public class RedState implements LightState {

    private double timer;

    @Override
    public void enter(TrafficLight light) {
        timer = 0;
        light.setRed(true);
    }

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;

        if (timer >= light.getStrategy().getRedDuration()) {
            light.setState(new YellowState()); // antes ia para GreenState
        }
    }


    @Override
    public String getName() {
        return "RED";
    }
}
