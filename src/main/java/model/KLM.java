package model;

import java.util.ArrayList;

public class KLM {
    ArrayList<Placemark> placemarks;
    ArrayList<Style> styles;

    public KLM(){
        this.placemarks = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    public KLM(ArrayList<Placemark> placemarks, ArrayList<Style> styles){
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
}
