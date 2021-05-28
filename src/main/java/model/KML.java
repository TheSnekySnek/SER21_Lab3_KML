package model;

import java.util.ArrayList;

public class KML {
    ArrayList<Placemark> placemarks;
    ArrayList<Style> styles;

    public KML(){
        this.placemarks = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    public KML(ArrayList<Placemark> placemarks, ArrayList<Style> styles){
        this.placemarks = placemarks;
        this.styles = styles;
    }

    public void setPlacemarks(ArrayList<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    public void setStyles(ArrayList<Style> styles) {
        this.styles = styles;
    }

    public void addStyle(Style style){
        this.styles.add(style);
    }

    public ArrayList<Placemark> getPlacemarks() {
        return placemarks;
    }

    public ArrayList<Style> getStyles() {
        return styles;
    }

    @Override
    public String toString() {
        return "KLM{" +
                "placemarks=\n" + placemarks +
                '}';
    }
}
