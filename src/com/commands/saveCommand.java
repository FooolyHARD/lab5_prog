package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;
import com.utils.CollectionManager;

public class saveCommand extends Command {
    private final CollectionManager collectionManager;

    public saveCommand(CollectionManager collectionManager) {
        super("save", "save to file");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.saveCollection();
            ConsoleManager.printSuccess("Collection was saved!");
            return true;
        } catch (ArgsAmountException
                exception) {
            System.out.println("Usage: '" + getName() + "'");
        }
        return false;
    }
}