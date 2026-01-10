package controller;

public class FixedCycle implements Strategy {

    @Override
    public double getRedDuration() {
        return 5;
    }

    @Override
    public double getGreenDuration() {
        return 5;
    }

    @Override
    public double getYellowDuration() {
        return 2;
    }
}
