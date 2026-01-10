package model;

public class Vehicle {

    private Road road;
    private double position = 0;
    private double speed;

    private double x;
    private double y;

    // distância em que o carro começa a considerar parar no cruzamento
    private double stopMargin = 40;

    public Vehicle(Road road, double speed) {
        this.road = road;
        this.speed = speed;
    }

    public void update(double deltaTime) {

        // Ver se há semáforo e se devo parar
        if (shouldStopForLight()) {
            // não avança, fica à espera
            return;
        }

        position += speed * deltaTime;

        if (position > road.getLength()) {
            position = road.getLength();
        }

        // Atualizar coordenadas gráficas com base na estrada
        if (isVertical()) {
            if (road.getName().equals("northToSouth")) {
                x = road.getStartX();
                y = road.getStartY() + position;
            } else if (road.getName().equals("southToNorth")) {
                x = road.getStartX();
                y = road.getStartY() - position;
            }
        } else {
            if (road.getName().equals("westToEast")) {
                x = road.getStartX() + position;
                y = road.getStartY();
            } else if (road.getName().equals("eastToWest")) {
                x = road.getStartX() - position;
                y = road.getStartY();
            }
        }
    }

    private boolean shouldStopForLight() {
        TrafficLight light = road.getTrafficLight();
        if (light == null) return false;

        double dist = distanceToIntersection();

        // Se estou perto do cruzamento e o semáforo não está verde, paro
        if (dist >= 0 && dist < stopMargin && !light.isGreenAllowed()) {
            // adiciona à fila
            road.addToQueue(this);
            return true;
        } else {
            // se já não estou a parar, removo da fila
            road.removeFromQueue(this);
            return false;
        }
    }

    private double distanceToIntersection() {
        double intersectionPos = road.getIntersectionPosition();
        // Carros vêm sempre no sentido crescente de "position" até ao cruzamento,
        // por isso a distância é simplesmente posição do cruzamento - posição atual.
        return intersectionPos - position;
    }

    public boolean isVertical() {
        return road.getName().equals("northToSouth") || road.getName().equals("southToNorth");
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double p) {
        this.position = p;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Road getRoad() {
        return road;
    }
}
