package view;

import controller.FixedCycle;
import controller.Simulation;
import controller.Strategy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Road;
import model.TrafficLight;
import model.Vehicle;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Simulation simulation = new Simulation();

        Road eastToWest   = new Road("eastToWest",   600);
        Road westToEast   = new Road("westToEast",   600);
        Road northToSouth = new Road("northToSouth", 600);
        Road southToNorth = new Road("southToNorth", 600);

        eastToWest.setStart(700, 300);
        westToEast.setStart(100, 300);
        northToSouth.setStart(400, 50);
        southToNorth.setStart(400, 550);

        simulation.addRoad(eastToWest);
        simulation.addRoad(westToEast);
        simulation.addRoad(northToSouth);
        simulation.addRoad(southToNorth);

        Strategy strategy = new FixedCycle();

        TrafficLight north = new TrafficLight("N", strategy);
        TrafficLight south = new TrafficLight("S", strategy);
        TrafficLight east  = new TrafficLight("E", strategy);
        TrafficLight west  = new TrafficLight("W", strategy);

        simulation.addLight(north);
        simulation.addLight(south);
        simulation.addLight(east);
        simulation.addLight(west);

        eastToWest.setTrafficLight(west);
        westToEast.setTrafficLight(east);
        northToSouth.setTrafficLight(north);
        southToNorth.setTrafficLight(south);

        Vehicle car1 = new Vehicle(eastToWest, 40);
        Vehicle car2 = new Vehicle(westToEast, 30);
        Vehicle car3 = new Vehicle(northToSouth, 25);
        Vehicle car4 = new Vehicle(southToNorth, 35);

        simulation.addVehicle(car1);
        simulation.addVehicle(car2);
        simulation.addVehicle(car3);
        simulation.addVehicle(car4);

        CanvasView canvas = new CanvasView(simulation);
        ControlPanel controls = new ControlPanel(canvas);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controls);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Smart Traffic Simulation - M3");
        stage.setScene(scene);
        stage.show();
    }
}
