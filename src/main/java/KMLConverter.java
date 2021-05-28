import model.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;

public class KMLConverter {
    KML kml;

    public KMLConverter(KML kml) {
        this.kml = kml;
    }

    public void createKMLFile() throws IOException {
        Document doc = new Document();
        Element elementKML = new Element("kml", Namespace.getNamespace("http://www.opengis.net/kml/2.2"));
        doc.setRootElement(elementKML);
        Element elementDocument = new Element("Document");
        elementKML.addContent(elementDocument);

        // Création de l'élément style
        Style style = kml.getStyles().get(0);
        Element elementStyle = new Element("Style");
        Element elementLineStyle = new Element("LineStyle");
        elementStyle.addContent(elementLineStyle);
        elementLineStyle.addContent(new Element("Width").setText(Double.toString(style.getLineWidth())));
        Element elementPolyStyle = new Element("PolyStyle");
        elementStyle.addContent(elementPolyStyle);
        elementPolyStyle.addContent(new Element("color").setText(style.getPolygonColor()));
        elementStyle.setAttribute("id", style.getId());
        elementDocument.addContent(elementStyle);

        for (Placemark placemark : kml.getPlacemarks()) {
            // Placemark placemark = kml.getPlacemarks().get(3);
            Element elementPlacemark = new Element("Placemark");
            elementPlacemark.addContent(new Element("name").setText(placemark.getName()));
            elementPlacemark.addContent(new Element("styleUrl").setText("#" + style.getId()));
            Element elementExtendedData = new Element("ExtendedData");
            elementExtendedData.addContent(new Element("Data").setText(placemark.getName()).setAttribute("name", "Country name"));
            elementExtendedData.addContent(new Element("Data").setText(placemark.getISO_A3()).setAttribute("name", "ISO name"));
            elementPlacemark.addContent(elementExtendedData);
            elementPlacemark.addContent(new Element("ISO_A3").setText(placemark.getISO_A3()));
            Element elementMultiGeometry = new Element("MultiGeometry");
            for (Polygon polygon : placemark.getPolygons()){
                Element elementPolygon = new Element("Polygon");
                elementMultiGeometry.addContent(elementPolygon);
                Element elementOuterBoundaryIs = new Element("outerBoundaryIs");
                Element elementLineaRing = new Element("LinearRing");
                Element elementCoordinate = new Element("coordinates");
                elementLineaRing.addContent(elementCoordinate);
                elementOuterBoundaryIs.addContent(elementLineaRing);
                elementPolygon.addContent(elementOuterBoundaryIs);
                String allCoordinates = "\n";
                for (Coordinate coord : polygon.getCoordinates()) {
                    allCoordinates += coord + "\n";
                }
                elementCoordinate.setText(allCoordinates);
            }
            elementPlacemark.addContent(elementMultiGeometry);

            elementDocument.addContent(elementPlacemark);
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        // xmlOutputter.output(doc, System.out);
        xmlOutputter.output(doc, new FileOutputStream(new File("countries.kml")));

        System.out.println("The KML file have been created");
    }
}
