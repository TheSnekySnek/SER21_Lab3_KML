import model.KML;
import model.Style;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public static void main(String[] args){
        String geojson;
        try{
            geojson = loadGeojson("countries.geojson");
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        KML kml = new KML();

        Style defaultStyle = new Style("default", "7dff0000", 1.5, "ffffff");
        kml.addStyle(defaultStyle);

        kml.setPlacemarks(GeoJsonLoader.getPlacemarks(geojson, "default"));

        try {
            new KMLConverter(kml).createKMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;

    }

    private static String loadGeojson(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
