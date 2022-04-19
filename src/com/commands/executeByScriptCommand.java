package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;

public class executeByScriptCommand extends Command {
    public executeByScriptCommand() {
        super("execute_script <file_name>", "use script from file");
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            ConsoleManager.printSuccess("Script is running '" + argument + "'...");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}