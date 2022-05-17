package com.utils;

import com.IO.ConsoleManager;

import com.parseXML.ReadXmlDomParser;
import com.parseXML.WriteXmlDomParser;
import com.sourcefiles.Vehicle;
import sun.awt.image.ImageWatched;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileManager {

    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeCollection(LinkedList<Vehicle> vehicleCollection) {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new IOException();
                WriteXmlDomParser.write(vehicleCollection, fileName);
            }
        } catch (IOException e) {
            ConsoleManager.printErr("No access to file");
            System.exit(0);
        }

    }

    public LinkedList<Vehicle> readCollection() {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new IOException();
                //System.out.println(ReadXmlDomParser.read(fileName));
            return ReadXmlDomParser.read(fileName);
            }
        } catch (IOException e) {
            ConsoleManager.printErr("No access to file");
            System.out.println(e);
            System.exit(0);
        }
        return new LinkedList<>();
    }
}

