package com.commands;

import com.exceptions.ArgsAmountException;
import com.IO.ConsoleManager;
public class helpCommand extends Command{
    public helpCommand(){
        super("help", "show full list of commands");
    }
    @Override
    public boolean execute(String arg){
        try {
            if (!arg.isEmpty()) throw new ArgsAmountException();
            return true;

        } catch (ArgsAmountException exception){
            ConsoleManager.printErr("Usgae: "+getName()+'"');
        }
        return false;
    }
}
