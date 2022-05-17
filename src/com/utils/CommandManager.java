package com.utils;

import com.commands.Command;
import java.util.LinkedList;
import java.util.List;
public class CommandManager {
    private final List<Command> commands = new LinkedList<>();
    private final Command helpCommand;
    private final Command infoCommand;
    private final Command showCommand;;
    private final Command addCommand;
    private final Command updateByIdCommand;
    private final Command removeByIdCommand;
    private final Command clearCommand;
    private final Command saveCommand;
    private final Command executeByScriptCommand;
    private final Command exitCommand;
    private final Command addIfminCommand;
    private final Command addIfmaxCommand;
    private final Command historyCommand;
    //TODO: Full cmd


    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand, Command updateByIdCommand, Command removeByIdCommand, Command clearCommand, Command saveCommand, Command executeByScriptCommand, Command exitCommand, Command addIfminCommand, Command addIfmaxCommand, Command historyCommand){
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateByIdCommand = updateByIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeByScriptCommand = executeByScriptCommand;
        this.exitCommand = exitCommand;
        this.addIfminCommand = addIfminCommand;
        this.addIfmaxCommand = addIfmaxCommand;
        this.historyCommand = historyCommand;
        //TODO: FUll cmd

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateByIdCommand);
        commands.add(updateByIdCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeByScriptCommand);
        commands.add(exitCommand);
        commands.add(addIfminCommand);
        commands.add(addIfmaxCommand);
        commands.add(historyCommand);
    }
    public List<Command> getCmd(){
        return commands;
    }
    public boolean noSuchCommand(String command) {
        System.out.println("Command '" + command + "' is incorrect. Enter 'help' for help :D.");
        return false;
    }
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }
    public boolean update(String argument) {
        return updateByIdCommand.execute(argument);
    }
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }
    public boolean executeScript(String argument) {
        return executeByScriptCommand.execute(argument);
    }
    public boolean addIfMin(String argument) {
        return addIfminCommand.execute(argument);
    }
    public boolean addIfmax(String argument) {
        return addIfmaxCommand.execute(argument);
    }
    public boolean history(String argument) {return historyCommand.execute(argument); }
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                System.out.println("Command name: " + command.getName() + ". Usage: " + command.getDisc());
            }
            return true;
        } else return false;
    }


}
