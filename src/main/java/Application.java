import model.KLM;
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

        KLM klm = new KLM();

        Style defaultStyle = new Style("default", "7dff0000", 1.5, "00ff0000");
        klm.addStyle(defaultStyle);

        klm.setPlacemarks(GeoJsonLoader.getPlacemarks(geojson, "default"));

        return;

    }

    private static String loadGeojson(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
