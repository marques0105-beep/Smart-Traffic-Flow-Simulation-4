package model;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

    private List<Road> roads = new ArrayList<>();

    public void addRoad(Road road) {
        roads.add(road);
    }

    public List<Road> getRoads() {
        return roads;
    }
}
