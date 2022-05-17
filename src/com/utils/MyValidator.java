package com.utils;

import com.IO.ConsoleManager;
import com.sourcefiles.Coordinates;
import com.sourcefiles.FuelType;
import com.sourcefiles.Vehicle;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;

public class MyValidator {

    public static void validateClass(LinkedList<Vehicle> collection) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        for (Vehicle vehicle : collection) {
            Set<ConstraintViolation<Coordinates>> validatedCoordinates = validator.validate(vehicle.getCoordinates());
            //Set<ConstraintViolation<FuelType>> validatedLocation = new HashSet<>();
            //if (vehicle.getFuelType() != null) {
              //  validatedLocation = validator.validate(vehicle.getFuelType());
            //}
            Set<ConstraintViolation<Vehicle>> validatedVehicle = validator.validate(vehicle);
            if (!validatedVehicle.isEmpty() || !validatedCoordinates.isEmpty()) {
                ConsoleManager.printErr("Incorrect file");
                validatedVehicle.stream().map(ConstraintViolation::getMessage)
                        .forEach(ConsoleManager::printErr);
                validatedCoordinates.stream().map(ConstraintViolation::getMessage)
                        .forEach(ConsoleManager::printErr);
               // validatedLocation.stream().map(ConstraintViolation::getMessage)
                 //       .forEach(ConsoleManager::printErr);
                System.exit(1);
            }
        }
        ConsoleManager.printSuccess("Start was successfull");
    }


}
