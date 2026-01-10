package model.state;

import model.TrafficLight;

public interface LightState {
    void update(TrafficLight light, double deltaTime);
    String getColor();
}
