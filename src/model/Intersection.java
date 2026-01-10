package model;

import java.util.List;

public class Intersection {

    private String id;
    private List<Road> roads;

    public Intersection(String id, List<Road> roads) {
        this.id = id;
        this.roads = roads;
    }

    public String getId() {
        return id;
    }

    public List<Road> getRoads() {
        return roads;
    }
}

