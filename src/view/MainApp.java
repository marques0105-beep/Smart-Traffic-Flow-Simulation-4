package view;

import controller.Simulation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Simulation simulation = new Simulation();
    private CanvasView canvasView = new CanvasView(simulation);

    private boolean running = true;
    private double simulationSpeed = 1.0;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setCenter(canvasView);

        // Slider para velocidade
        Slider speedSlider = new Slider(0.1, 5, 1);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            simulationSpeed = newVal.doubleValue();
        });

        // Painel de controlo
        ControlPanel controlPanel = new ControlPanel(
                () -> running = true,
                () -> running = false,
                () -> simulation = new Simulation(), // reset
                speedSlider
        );
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 900, 450);

        AnimationTimer timer = new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) last = now;
                double dt = (now - last) / 1e9 * simulationSpeed;
                last = now;

                if (running) {
                    simulation.update(dt);
                }

                canvasView.render();
            }
        };
        timer.start();

        stage.setTitle("Smart Traffic Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

