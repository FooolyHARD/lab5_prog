package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;
import com.exceptions.NullColletionEcxeption;
import com.exceptions.VehicleIsNullException;
import com.sourcefiles.Vehicle;
import com.utils.CollectionManager;

public class removeByIdCommand extends Command {
    private final CollectionManager collectionManager;

    public removeByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "remove element by ID");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            Long id = Long.parseLong(argument);
            Vehicle vehicle = collectionManager.getById(id);
            if (vehicle == null) throw new VehicleIsNullException();
            collectionManager.removeById(vehicle);
            ConsoleManager.printSuccess("Vehicle was removed!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Colletion is empty!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be integer!");
        } catch (VehicleIsNullException exception) {
            ConsoleManager.printErr("No vehicle with that ID");
        }
        return false;
    }
}