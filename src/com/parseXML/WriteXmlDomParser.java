package com.parseXML;

import com.sourcefiles.Coordinates;
import com.sourcefiles.FuelType;
import com.sourcefiles.Vehicle;
import com.sourcefiles.VehicleType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class WriteXmlDomParser {

    private static String FILENAME;

    private static Element serializeVehicle(Vehicle vehicle, Document document){
        Element root = document.createElement("vehicle");

        Element id = document.createElement("id");
        root.appendChild(id);
        id.setTextContent(String.valueOf(vehicle.getId()));

        Element name = document.createElement("name");
        root.appendChild(name);
        name.setTextContent(vehicle.getName());

        Element creationDate = document.createElement("creationDate");
        root.appendChild(creationDate);
        creationDate.setTextContent(vehicle.getCreationDate().toString());

        Element enginePower = document.createElement("enginePower");
        root.appendChild(enginePower);
        enginePower.setTextContent(String.valueOf(vehicle.getEnginePower()));

        Element type = document.createElement("type");
        root.appendChild(type);
        type.setTextContent(vehicle.getType().name());

        Element vehicleType = document.createElement("fuelType");
        root.appendChild(vehicleType);
        vehicleType.setTextContent(vehicle.getFuelType().name());

        Element coordinates = document.createElement("coordinates");
        root.appendChild(coordinates);

        Element coordinatesX = document.createElement("coordinatesX");
        coordinates.appendChild(coordinatesX);
        coordinatesX.setTextContent(String.valueOf(vehicle.getCoordinates().getX()));

        Element coordinatesY = document.createElement("coordinatesY");
        coordinates.appendChild(coordinatesY);
        coordinatesY.setTextContent(String.valueOf(vehicle.getCoordinates().getY()));

        return root;
    }

    public static void write(List<Vehicle> list, String FILENAME) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element vehicles = doc.createElement("vehicles");
            doc.appendChild(vehicles);

            for(Vehicle vehicle: list){
                vehicles.appendChild(serializeVehicle(vehicle, doc));
            }

            DOMSource dom = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            StreamResult streamResult = new StreamResult(new File(FILENAME));
            transformer.transform(dom, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Жалко пиздец: ");
            e.printStackTrace();
        }
    }
}
