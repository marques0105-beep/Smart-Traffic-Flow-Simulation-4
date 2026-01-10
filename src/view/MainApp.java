package view;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainApp extends Application {

    private long lastTime = 0;

    @Override
    public void start(Stage stage) {

        TrafficLight light;
        Road road;
        Sensor sensor;

        light = new TrafficLight(null);
        road = new Road(light);
        sensor = new Sensor(road);

        light = new TrafficLight(new AdaptiveCycle(sensor));

        Simulation sim = new Simulation();

        Vehicle normal = new Vehicle(road, 60, false);
        Vehicle emergency = new Vehicle(road, 80, true);

        sim.addLight(light);
        sim.addVehicle(normal);
        sim.addVehicle(emergency);

        Canvas canvas = new Canvas(600, 400);
        CanvasView view = new CanvasView(canvas);

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");

        Slider speed = new Slider(0.2, 3, 1);

        start.setOnAction(e -> sim.start());
        stop.setOnAction(e -> sim.stop());
        reset.setOnAction(e -> sim.reset());

        speed.valueProperty().addListener((obs, o, n) ->
                sim.setSpeedMultiplier(n.doubleValue())
        );

        ToolBar bar = new ToolBar(start, stop, reset, new Label("Speed"), speed);

        BorderPane root = new BorderPane();
        root.setTop(bar);
        root.setCenter(canvas);

        stage.setScene(new Scene(root));
        stage.setTitle("Smart Traffic Flow Simulation");
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double delta = (now - lastTime) / 1e9;
                lastTime = now;

                sim.update(delta);
                view.render(sim);
            }
        }.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
