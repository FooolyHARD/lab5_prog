package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;
import com.utils.CollectionManager;

public class clearCommand extends Command{
    private final CollectionManager collectionManager;

    public clearCommand(CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.clearCollection();
            ConsoleManager.printSuccess("Collection is cleared!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}