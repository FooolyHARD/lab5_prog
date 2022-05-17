package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;
import com.exceptions.NullColletionEcxeption;
import com.utils.CollectionManager;

public class showCommand extends Command {
    private final CollectionManager collectionManager;

    public showCommand(CollectionManager collectionManager) {
        super("show", "Show collection");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            System.out.println(collectionManager.toString());
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is empty!");
        }
        return false;
    }
}