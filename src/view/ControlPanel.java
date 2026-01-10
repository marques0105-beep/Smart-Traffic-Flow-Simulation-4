package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ControlPanel extends HBox {

    public ControlPanel(CanvasView canvas) {
        setSpacing(15);
        setPadding(new Insets(10));

        // Botões
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        Button restartBtn = new Button("Restart");

        startBtn.setOnAction((ActionEvent e) -> canvas.startSimulation());
        stopBtn.setOnAction((ActionEvent e) -> canvas.stopSimulation());
        restartBtn.setOnAction((ActionEvent e) -> canvas.restartSimulation());

        // Slider de velocidade
        Label speedLabel = new Label("Velocidade:");

        Slider speedSlider = new Slider(0.1, 3.0, 1.0);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.5);
        speedSlider.setMinorTickCount(4);

        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setSpeedFactor(newVal.doubleValue());
        });

        getChildren().addAll(startBtn, stopBtn, restartBtn, speedLabel, speedSlider);
    }
}

