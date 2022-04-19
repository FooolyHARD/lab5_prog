package com.parseXML;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomExample {
    public static LinkedList startparse(String filename) {
        LinkedList<LinkedList> fullList = new LinkedList<LinkedList>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            Node root = document.getDocumentElement();
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                LinkedList<Object> VecSpec = new LinkedList<Object>();
                Node book = books.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            VecSpec.add(bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                }
                if (VecSpec.size() !=0){
                    fullList.add(VecSpec);
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        for (int i = 0; i<fullList.size(); i++){
            fullList.get(i).add(0, i+1);
            fullList.get(i).add(3, LocalDateTime.now());
        }
    return fullList;
    }

}