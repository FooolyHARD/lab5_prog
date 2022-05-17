package com.commands;

import com.IO.ConsoleManager;
import com.IO.ScannerManager;
import com.exceptions.ArgsAmountException;
import com.exceptions.BadScriptException;
import com.exceptions.NotMinException;
import com.exceptions.NullColletionEcxeption;
import com.sourcefiles.Vehicle;
import com.utils.CollectionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class addIfminCommand extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;


    public addIfminCommand(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("add_if_min {element}", "add a new element which smaller then the smallest in collection");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            Vehicle vehicleToAdd = new Vehicle(
                    collectionManager.generateNextId(),
                    scannerManager.askPersonName(),
                    scannerManager.askCoordinates(),
                    LocalDate.now(),
                    scannerManager.askEnginePower(),
                    scannerManager.askVehicleType(),
                    scannerManager.askFuelType()
            );
            if (vehicleToAdd.compareTo(collectionManager.getLast()) < 0)
                collectionManager.addToCollection(vehicleToAdd);
            else
                throw new NotMinException();
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is empty");
        } catch (NotMinException exception) {
            ConsoleManager.printErr("That element is not bigger");
        } catch (BadScriptException exception) {
            ConsoleManager.printErr("Bad scipt");
        }
        return false;

    }
}