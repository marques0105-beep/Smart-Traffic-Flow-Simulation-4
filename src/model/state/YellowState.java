package model.state;

import model.TrafficLight;

public class YellowState implements LightState {

    private double timer;

    @Override
    public void enter(TrafficLight light) {
        timer = 0;
        light.setRed(false);
    }

    @Override
    public void update(TrafficLight light, double deltaTime) {
        timer += deltaTime;

        if (timer >= light.getStrategy().getYellowDuration()) {
            light.setState(new GreenState()); // antes ia para RedState
        }
    }


    @Override
    public String getName() {
        return "YELLOW";
    }
}
