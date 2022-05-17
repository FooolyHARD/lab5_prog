package com.commands;

import com.IO.ConsoleManager;
import com.IO.ScannerManager;
import com.exceptions.ArgsAmountException;
import com.exceptions.BadScriptException;
import com.exceptions.NullColletionEcxeption;
import com.exceptions.VehicleIsNullException;
import com.sourcefiles.Coordinates;
import com.sourcefiles.FuelType;
import com.sourcefiles.Vehicle;
import com.sourcefiles.VehicleType;
import com.utils.CollectionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class updateByIdCommand extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public updateByIdCommand(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("update <ID> {element}", "update element by ID");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();

            Long id = Long.parseLong(argument);
            Vehicle vehicle = collectionManager.getById(id);
            if (vehicle == null) throw new VehicleIsNullException();
            String name = vehicle.getName();
            Coordinates coordinates = vehicle.getCoordinates();
            LocalDate creationDate = vehicle.getCreationDate();
            float enginePower = vehicle.getEnginePower();
            VehicleType type = vehicle.getType();
            FuelType fuelType = vehicle.getFuelType();


            collectionManager.removeFromCollection(vehicle);

            if (scannerManager.askQuestion("Change Vehicle name?"))
                name = scannerManager.askPersonName();
            if (scannerManager.askQuestion("Change Vehicle coordinates?"))
                coordinates = scannerManager.askCoordinates();
            if (scannerManager.askQuestion("Change Vehicle engine power?"))
                enginePower = scannerManager.askEnginePower();
            if (scannerManager.askQuestion("Change Vehicle fuel type?"))
                fuelType = scannerManager.askFuelType();
            if (scannerManager.askQuestion("Change Vehicle type?"))
                type = scannerManager.askVehicleType();

            collectionManager.addToCollection(new Vehicle(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    enginePower,
                    type,
                    fuelType
            ));
            ConsoleManager.printSuccess("Info was successfully updated");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is  NULL!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be an integer!");
        } catch (VehicleIsNullException exception) {
            ConsoleManager.printErr("No such Vehicle with that ID");
        } catch (BadScriptException exception) {
            ConsoleManager.printErr("Script is bad.");
        }
        return false;
    }
}