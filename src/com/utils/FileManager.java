package com.utils;

import com.IO.ConsoleManager;
import com.parseXML.DomExample;
import com.sourcefiles.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeCollection(LinkedList<Vehicle> VehicleCollection) {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new IOException();

                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                String result = null; //TODO: СПРОСИ У ИЛЬДАРА ЧТО ТУТ
                writer.write(result);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            ConsoleManager.printErr("No access to file");
            System.exit(0);
        }

    }

    /**
     * Reads collection from a file.
     *
     * @return Readed collection.
     */
    public LinkedList<Vehicle> readCollection() {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.canRead() || !file.canWrite())
                    throw new IOException();
                InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
                reader.close();
                LinkedList<Vehicle> convertedCollection = DomExample.startparse(fileName);
                if (convertedCollection == null)
                    convertedCollection = new LinkedList<>();
                else
                    MyValidator.validateClass(convertedCollection);

                return convertedCollection;
            }
        } catch (IOException e) {
            ConsoleManager.printErr("Нет доступа к файлу!");
            System.exit(0);
        }
        return new LinkedList<>();
    }
}

