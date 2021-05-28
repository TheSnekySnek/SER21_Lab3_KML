import model.Coordinate;
import model.Placemark;
import model.Polygon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GeoJsonLoader {
    public static ArrayList<Placemark> getPlacemarks(String geojson, String styleId){
        ArrayList<Placemark> placemarks = new ArrayList<>();

        JSONObject geo = new JSONObject(geojson);
        JSONArray features = geo.getJSONArray("features");

        for (Object featureObj: features) {
            JSONObject feature = (JSONObject) featureObj;

            // We only care about features
            String tp = feature.getString("type");
            if(!tp.equals("Feature"))
                continue;

            JSONObject properties = feature.getJSONObject("properties");
            JSONObject geometry = feature.getJSONObject("geometry");

            String name = properties.getString("ADMIN");
            String type = geometry.getString("type");
            String ISO_A3 = properties.getString("ISO_A3");

            Placemark placemark = new Placemark(name, ISO_A3, type, styleId);

            if(type.equals("Polygon")){
                Polygon polygon = new Polygon();
                JSONArray coordinates = geometry.getJSONArray("coordinates").getJSONArray(0);
                for (Object coordinateObj: coordinates) {

                    JSONArray coordinate = (JSONArray) coordinateObj;
                    Coordinate coord = new Coordinate(coordinate.getDouble(0), coordinate.getDouble(1));
                    polygon.addCoordinate(coord);
                }
                placemark.addPolygon(polygon);
            }
            else if(type.equals("MultiPolygon")){

                JSONArray polygons = geometry.getJSONArray("coordinates");

                // This needs to be combined with the other one
                for (Object polygonObj: polygons) {
                    Polygon polygon = new Polygon();
                    JSONArray coordinates = ((JSONArray) polygonObj).getJSONArray(0);
                    for (Object coordinateObj: coordinates) {
                        JSONArray coordinate = (JSONArray) coordinateObj;
                        Coordinate coord = new Coordinate(coordinate.getDouble(0), coordinate.getDouble(1));
                        polygon.addCoordinate(coord);
                    }
                    placemark.addPolygon(polygon);
                }
            }

            placemarks.add(placemark);

        }

        return placemarks;
    }
}
