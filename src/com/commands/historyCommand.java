package com.commands;

import com.IO.ConsoleManager;
import com.exceptions.ArgsAmountException;
import com.utils.Chronicler;

public class historyCommand extends Command {
    private final Chronicler chronicler;

    public historyCommand(Chronicler chronicler) {
        super("history", "View last 12 commands");
        this.chronicler = chronicler;
    }

    @Override
    public boolean execute(String argument) {
        String[] history = chronicler.getHistory();
        System.out.println("Last 13 commands:");
        for (String s : history) {
            if (!(s == null)) {
                ConsoleManager.printSuccess(s);
            }
        }

    return true;
    }
}