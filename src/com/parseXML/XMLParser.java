package com.parseXML;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sourcefiles.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLParser {
    public static void startparse(String filepath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Vehicle.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Vehicle vehicle = (Vehicle) jaxbUnmarshaller.unmarshal(new File(filepath));
    }
}