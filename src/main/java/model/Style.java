package model;

public class Style {
    String id;

    String lineColor;
    double lineWidth;

    String polygonColor;

    public Style(String id, String lineColor, double lineWidth, String polygonColor){
        this.id = id;
        this.lineColor = lineColor;
        this.lineWidth = lineWidth;
        this.polygonColor = polygonColor;
    }

    public String getId() {
        return id;
    }

    public String getLineColor() {
        return lineColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public String getPolygonColor() {
        return polygonColor;
    }
}
