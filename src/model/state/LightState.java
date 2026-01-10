package model.state;

import model.TrafficLight;

public interface LightState {
    void enter(TrafficLight light);
    void update(TrafficLight light, double deltaTime);
    String getName();
}
