package model;

import java.util.ArrayList;

public class Placemark {

    private String name;
    private String ISO_A3;
    private String styleId;
    private String type;
    private ArrayList<Polygon> polygons;

    public Placemark(String name, String ISO_A3, String type, String styleId){
        this.name = name;
        this.ISO_A3 = ISO_A3;
        this.type = type;
        this.styleId = styleId;
        this.polygons = new ArrayList<>();
    }

    public void addPolygon(Polygon polygon){
        this.polygons.add(polygon);
    }

    @Override
    public String toString() {
        return "Placemark{" +
                "name='" + name + '\'' +
                ", ISO_A3='" + ISO_A3 + '\'' +
                ", styleId='" + styleId + '\'' +
                ", type='" + type + '\'' +
                ", polygons=" + polygons.get(0).getCoordinates().size()  +
                "}\n";
    }
}
