package view;

import controller.Simulation;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.TrafficLight;
import model.Vehicle;

public class CanvasView extends Canvas {

    private Simulation simulation;
    private AnimationTimer timer;
    private double speedFactor = 1.0;

    public CanvasView(Simulation simulation) {
        super(800, 600);
        this.simulation = simulation;
        setupAnimation(); }

    private void setupAnimation() {
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                    deltaTime *= speedFactor;
                    simulation.update(deltaTime);
                    draw();
                }
                lastUpdate = now;
            }
        };
        timer.start();
    }

    public void startSimulation() {
        timer.start();
    }

    public void stopSimulation() {
        timer.stop();
    }

    public void restartSimulation() {
        timer.stop();
        // Se tiveres reset de mundo, chamavas aqui (world.reset(), etc.)
        timer.start();
    }

    public void setSpeedFactor(double factor) {
        this.speedFactor = factor;
    }

    private void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        drawRoads(gc);
        drawTrafficLights(gc);
        drawVehicles(gc);
    }

    // ============================
    // ESTRADAS COM LANES
    // ============================
    private void drawRoads(GraphicsContext gc) {

        // Asfalto
        gc.setFill(Color.web("#2f2f2f"));
        gc.fillRect(100, 250, 600, 100); // horizontal
        gc.fillRect(350, 50, 100, 500);  // vertical

        // Cruzamento
        gc.setFill(Color.web("#3a3a3a"));
        gc.fillRect(350, 250, 100, 100);

        // Bordas
        gc.setStroke(Color.web("#1a1a1a"));
        gc.setLineWidth(4);
        gc.strokeRect(100, 250, 600, 100);
        gc.strokeRect(350, 50, 100, 500);

        // Lanes horizontais
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);

        // Faixas contínuas horizontal (topo e base)
        gc.strokeLine(100, 260, 700, 260);
        gc.strokeLine(100, 340, 700, 340);

        // Faixa central tracejada horizontal
        gc.setLineDashes(20);
        gc.setLineWidth(3);
        gc.strokeLine(100, 300, 700, 300);
        gc.setLineDashes(0);

        // Lanes verticais
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);

        // Faixas contínuas vertical (esquerda e direita)
        gc.strokeLine(360, 50, 360, 550);
        gc.strokeLine(440, 50, 440, 550);

        // Faixa central tracejada vertical
        gc.setLineDashes(20);
        gc.setLineWidth(3);
        gc.strokeLine(400, 50, 400, 550);
        gc.setLineDashes(0);
    }

    // ============================
    // Passadeiras
    // ============================
    private void drawCrosswalk(GraphicsContext g, double x, double y, double w, double h) {
        int stripes = 6;
        g.setFill(Color.WHITE);
        if (w > h) {
            double stripeW = w / (stripes * 2.0);
            for (int i = 0; i < stripes; i++) {
                double sx = x + i * 2 * stripeW;
                g.fillRect(sx, y, stripeW, h);
            }
        } else {
            double stripeH = h / (stripes * 2.0);
            for (int i = 0; i < stripes; i++) {
                double sy = y + i * 2 * stripeH;
                g.fillRect(x, sy, w, stripeH);
            }
        }
    }

    // ============================
    // SEMÁFOROS
    // ============================
    private void drawTrafficLights(GraphicsContext gc) {

        for (TrafficLight light : simulation.getLights()) {

            double x = 0, y = 0;

            switch (light.getId()) {
                case "N": x = 390; y = 220; break;
                case "S": x = 390; y = 360; break;
                case "E": x = 470; y = 290; break;
                case "W": x = 310; y = 290; break;
            }

            // Caixa do semáforo
            gc.setFill(Color.BLACK);
            gc.fillRoundRect(x - 5, y - 5, 30, 50, 8, 8);

            // Moldura
            gc.setStroke(Color.DARKGRAY);
            gc.setLineWidth(2);
            gc.strokeRoundRect(x - 5, y - 5, 30, 50, 8, 8);

            if (light.getStateName().equals("RED")) {
                gc.setFill(Color.RED);
            } else if (light.getStateName().equals("YELLOW")) {
                gc.setFill(Color.YELLOW);
            } else {
                gc.setFill(Color.LIMEGREEN);
            }

            gc.fillOval(x, y, 20, 20);
        }
    }

    // ============================
    // VEÍCULOS
    // ============================
    private void drawVehicles(GraphicsContext gc) {

        for (Vehicle v : simulation.getVehicles()) {

            double x = v.getX();
            double y = v.getY();

            // Cor básica por direção
            Color color = v.isVertical() ? Color.RED : Color.CYAN;

            // Carro
            gc.setFill(color);

            if (v.isVertical()) {
                gc.fillRoundRect(x - 10, y - 15, 20, 30, 5, 5);
                gc.setFill(Color.BLACK);
                gc.fillRect(x - 6, y - 13, 12, 6); // capot
            } else {
                gc.fillRoundRect(x - 15, y - 10, 30, 20, 5, 5);
                gc.setFill(Color.BLACK);
                gc.fillRect(x - 13, y - 6, 6, 12); // capot
            }
        }
    }
}
