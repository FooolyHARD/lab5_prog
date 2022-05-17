package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;

public class exitCommand extends Command {

    public exitCommand() {
        super("exit", "finish program without saving");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            ConsoleManager.printSuccess("Good Bye");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}