package com.commands;

import com.IO.ConsoleManager;
import com.IO.ScannerManager;
import com.exceptions.ArgsAmountException;
import com.exceptions.BadScriptException;
import com.sourcefiles.Vehicle;
import com.utils.CollectionManager;

import java.time.LocalDateTime;

public class addCommand extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public addCommand(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("add {element}", "add a new element to colletion");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.addToCollection(
                    new Vehicle(
                            collectionManager.generateNextId(),
                            scannerManager.askPersonName(),
                            scannerManager.askCoordinates(),
                            LocalDateTime.now(),
                            scannerManager.askEnginePower(),
                            scannerManager.askVehicleType(),
                            scannerManager.askFuelType()
                    )
            );
            ConsoleManager.printSuccess("Data added successfully!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (BadScriptException exception) {
            ConsoleManager.printErr("Bad script");
        }
        return false;
    }

}