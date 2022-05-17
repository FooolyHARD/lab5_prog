package com.parseXML;

import com.sourcefiles.Coordinates;
import com.sourcefiles.FuelType;
import com.sourcefiles.Vehicle;
import com.sourcefiles.VehicleType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ReadXmlDomParser {

    private static Coordinates getCoordinatesFrom(Node node) {
        Coordinates res = new Coordinates();

        for(int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node cur = node.getChildNodes().item(i);

            switch (cur.getNodeName()) {
                case "coordinatesX":
                    res.setX(Double.parseDouble(cur.getTextContent()));
                    break;
                case "coordinatesY":
                    res.setY(Integer.parseInt(cur.getTextContent()));
                    break;
            }
        }
        return res;
    }

    private static Vehicle getVehicleFrom(Node node){
        Vehicle res = new Vehicle(0,null,null,null,0,null,null);

        for(int i = 0; i < node.getChildNodes().getLength(); i++){
            Node cur = node.getChildNodes().item(i);

            switch (cur.getNodeName()) {
                case "id":
                    res.setId(Integer.parseInt(cur.getTextContent()));
                    break;
                case "name":
                    res.setName(cur.getTextContent());
                    break;
                case "creationDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    res.setCreationDate(LocalDate.parse(cur.getTextContent(), formatter));
                    break;
                case "enginePower":
                    res.setEnginePower(Float.parseFloat(cur.getTextContent()));
                    break;
                case "type":
                    res.setType(VehicleType.valueOf(cur.getTextContent()));
                    break;
                case "fuelType":
                    res.setFuelType(FuelType.valueOf(cur.getTextContent()));
                    break;
                case "coordinates":
                    res.setCoordinates(getCoordinatesFrom(cur));
                    break;
            }
        }

        return res;
    }

    public static LinkedList<Vehicle> read(String FILENAME) {
        // READ
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try{
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();


            NodeList list = doc.getElementsByTagName("vehicle");

            LinkedList<Vehicle> res = new LinkedList<>();

            for(int i = 0; i < list.getLength(); i++){
                Node node = list.item(i);

                res.add(getVehicleFrom(node));
                //System.out.println(res);
            }
        return res;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
        return new LinkedList<>();
    }


}
