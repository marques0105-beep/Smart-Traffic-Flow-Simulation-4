package view;

import controller.Simulation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Vehicle;

public class CanvasView {

    private Canvas canvas;
    private GraphicsContext gc;

    public CanvasView(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void render(Simulation sim) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Vehicle v : sim.getVehicles()) {
            gc.setFill(v.isEmergency() ? Color.RED : Color.BLUE);
            gc.fillRect(v.getPosition(), 200, 30, 15);
        }
    }
}
