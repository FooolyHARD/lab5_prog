package com.utils;

import com.IO.ConsoleManager;

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

                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                JAXBContext context = JAXBContext.newInstance(Vehicle.class);
                Marshaller mr = context.createMarshaller();

                mr.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                for (Vehicle v: vehicleCollection){
                    mr.marshal(v, writer);
                    writer.flush();
                }
                writer.close();
            }
        } catch (IOException | JAXBException e) {
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
                JAXBContext context = JAXBContext.newInstance(Vehicle.class);
                Unmarshaller um = context.createUnmarshaller();

            }
        } catch (IOException | JAXBException e) {
            ConsoleManager.printErr("No access to file");
            System.out.println(e);
            //System.exit(0);
        }
        return new LinkedList<>();
    }
}

