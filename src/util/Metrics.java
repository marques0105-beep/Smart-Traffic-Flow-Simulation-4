package util;

public class Metrics {

    private double totalWaitingTime = 0;
    private int servedCars = 0;

    public void addWaitingTime(double time) {
        totalWaitingTime += time;
    }

    public void incrementServedCars() {
        servedCars++;
    }

    public double getAverageWaitingTime() {
        return servedCars == 0 ? 0 : totalWaitingTime / servedCars;
    }

    public int getServedCars() {
        return servedCars;
    }

    public void reset() {
        totalWaitingTime = 0;
        servedCars = 0;
    }
}
