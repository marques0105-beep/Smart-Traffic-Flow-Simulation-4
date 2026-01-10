package controller;

public class FixedCycle implements Strategy {

    public double getRedDuration() { return 3; }
    public double getGreenDuration() { return 4; }
    public double getYellowDuration() { return 1; }
}
