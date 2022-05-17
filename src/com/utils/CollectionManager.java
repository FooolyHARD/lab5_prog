package com.utils;

import com.sourcefiles.FuelType;
import com.sourcefiles.VehicleType;
import com.sourcefiles.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CollectionManager {
    private LinkedList<Vehicle> VehicleCollection = new LinkedList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }


    public LinkedList<Vehicle> getCollection() {
        return VehicleCollection;
    }


    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }


    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }


    public String collectionType() {
        return VehicleCollection.getClass().getName();
    }


    public int collectionSize() {
        return VehicleCollection.size();
    }


    public Vehicle getLast() {
        if (VehicleCollection.isEmpty()) return null;
        return VehicleCollection.getLast();
    }


    public Vehicle getById(Long id) {
        for (Vehicle vehicle : VehicleCollection) {
            if (vehicle.getId().equals(id)) return vehicle;
        }
        return null;
    }


    public void removeById(Vehicle person) {
        LinkedList<Vehicle> tmp = new LinkedList<>();
        while (!VehicleCollection.isEmpty() && !VehicleCollection.getFirst().getId().equals(person.getId())) {
            tmp.addLast(VehicleCollection.pollFirst());
        }
        VehicleCollection.removeFirst();
        while (!tmp.isEmpty()) VehicleCollection.addFirst(tmp.pollLast());
    }



    public void addToCollection(Vehicle person) {
        LinkedList<Vehicle> tmp = new LinkedList<>();
        while (!VehicleCollection.isEmpty() && VehicleCollection.getFirst().compareTo(person) < 0) {
            tmp.addLast(VehicleCollection.pollFirst());
        }
        VehicleCollection.addFirst(person);
        while (!tmp.isEmpty()) VehicleCollection.addFirst(tmp.pollLast());
    }



    public void clearCollection() {
        VehicleCollection.clear();
    }
    public Long generateNextId() {
        if (VehicleCollection.isEmpty()) return 1L;
        return VehicleCollection.getLast().getId() + 1;
    }



    public void saveCollection() {
        fileManager.writeCollection(VehicleCollection);
        lastSaveTime = LocalDateTime.now();
    }


    private void loadCollection() {
       VehicleCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }


    public void removeFromCollection(Vehicle personToRemove) {
        LinkedList<Vehicle> tmp = new LinkedList<>();
        while (!personToRemove.equals(VehicleCollection.getFirst())) {
            tmp.addLast(VehicleCollection.pollFirst());
        }
        VehicleCollection.removeFirst();
        while (!tmp.isEmpty()) VehicleCollection.addFirst(tmp.pollLast());
    }


    @Override
    public String toString() {
        if (VehicleCollection.isEmpty()) return "Colletion is empty!";
        StringBuilder info = new StringBuilder();
        //System.out.println(VehicleCollection.get(0));
        for (Vehicle vehicle : VehicleCollection) {
            info.append(vehicle.toString());
            if (vehicle != VehicleCollection.getLast()) {
                info.append("\n\n");
            }
        }
        return info.toString();
    }

}