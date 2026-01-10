package util;

public class Metrics {

    private int totalVehiclesPassed;
    private double totalWaitingTime;

    public void addVehiclePassed() {
        totalVehiclesPassed++;
    }

    public void addWaitingTime(double time) {
        totalWaitingTime += time;
    }

    public int getTotalVehiclesPassed() {
        return totalVehiclesPassed;
    }

    public double getAverageWaitingTime() {
        if (totalVehiclesPassed == 0) return 0;
        return totalWaitingTime / totalVehiclesPassed;
    }
}
