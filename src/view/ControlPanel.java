package view;

import controller.Simulation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;

public class ControlPanel extends ToolBar {

    public ControlPanel(Simulation simulation) {

        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        Button restartBtn = new Button("Reset");

        startBtn.setOnAction(e -> simulation.start());
        stopBtn.setOnAction(e -> simulation.stop());
        restartBtn.setOnAction(e -> simulation.reset());

        Label speedLabel = new Label("Velocidade:");

        Slider speedSlider = new Slider(0.1, 3.0, 1.0);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.5);
        speedSlider.setMinorTickCount(4);

        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setSpeedMultiplier(newVal.doubleValue());
        });

        getItems().addAll(startBtn, stopBtn, restartBtn, speedLabel, speedSlider);
    }
}

