package model.state;

import model.TrafficLight;

public class GreenState implements LightState {

    private double timer;

    @Override
    public void enter(TrafficLight light) {
        timer = 0;
        light.setRed(false);
    }

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;

        if (timer >= light.getStrategy().getGreenDuration()) {
            light.setState(new RedState()); // antes ia para YellowState
        }
    }


    @Override
    public String getName() {
        return "GREEN";
    }
}
