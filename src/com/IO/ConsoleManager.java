package com.IO;

import com.exceptions.BadAccessToFileException;
import com.exceptions.ScriptRecurentException;
import com.utils.Chronicler;
import com.utils.CommandManager;
import com.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager {
    private final CommandManager cmdManager;
    private final Scanner scanner;
    private final ScannerManager scannerManager;
    private final List<String> scriptStack = new LinkedList<>();
    private final Chronicler chronicler;
    public ConsoleManager(CommandManager cmdManager, Scanner scanner, ScannerManager scannerManager,Chronicler chronicler) {
        this.cmdManager = cmdManager;
        this.scanner = scanner;
        this.scannerManager = scannerManager;
        this.chronicler = chronicler;
    }

    public static void printErr(Object message) {
        System.out.println("\u001B[31m" + "Error: " + message + "\u001B[0m");
    }

    public static void printSuccess(Object message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void printInfo(Object message) {
        System.out.println("\u001B[36m" + message + "\u001B[0m");
    }


    private int launchCommand(String[] userCommand) {
        String command = userCommand[0];
        String argument = userCommand[1];
        switch (command) {
            case "help":
                if (!cmdManager.help(argument)){
                    chronicler.addInHistory("help");
                return 1;}
                break;
            case "":
                ConsoleManager.printErr("Empty stroke");
                break;
            case "info":
                if (!cmdManager.info(argument)) {chronicler.addInHistory("info");
                    return 1;}
                break;
            case "add":
                if (!cmdManager.add(argument)) {chronicler.addInHistory("add");
                    return 1;}
                break;
            case "update":
                if (!cmdManager.update(argument)) { chronicler.addInHistory("update");return 1;}
                break;
            case "remove_by_id":
                if (!cmdManager.removeById(argument)) {chronicler.addInHistory("remove_by_id");return 1;};
                break;
            case "clear":
                if (!cmdManager.clear(argument)) {chronicler.addInHistory("clear");return 1;}
                break;
            case "save":
                if (!cmdManager.save(argument)) {chronicler.addInHistory("save");return 1;}
                break;
            case "add_if_min":
                if (!cmdManager.addIfMin(argument)) {chronicler.addInHistory("add_if_min");return 1;}
                break;
            case "add_if_max":
                if (!cmdManager.addIfmax(argument)) { chronicler.addInHistory("add_if_max");return 1;}
                break;
            case "exit":
                if (!cmdManager.exit(argument)) {chronicler.addInHistory("exit");return 1;}
                break;
            case "execute_script":
                if (!cmdManager.executeScript(argument)) { chronicler.addInHistory("execute_script");return 1;}
                else return scriptMode(argument);
            case "show":
                if (!cmdManager.show(argument)) {chronicler.addInHistory("show"); return 1;}
                break;
            case "history":
                if (!cmdManager.history(argument)) {chronicler.addInHistory("history");return 1;}
                break;
            default:
                chronicler.addInHistory("Unknown command");
                ConsoleManager.printErr("No such command as in list");
                break;
        }
    return 0;
    }
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                System.out.println(Main.INPUT_COMMAND);
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            ConsoleManager.printErr("Users enter didnt detected");
        } catch (IllegalStateException exception) {
            ConsoleManager.printErr("Unexpected error");
        }
    }

    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try {
            File file = new File(argument);
            if (file.exists() && !file.canRead()) throw new BadAccessToFileException();
            Scanner scriptScanner = new Scanner(file);
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = scannerManager.getUserScanner();
            scannerManager.setUserScanner(scriptScanner);
            scannerManager.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                System.out.println(Main.INPUT_COMMAND + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecurentException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            scannerManager.setUserScanner(tmpScanner);
            scannerManager.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                System.out.println("Script is incorrect");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            ConsoleManager.printErr("No such file with script");
        } catch (NoSuchElementException exception) {
            ConsoleManager.printErr("File is empty!");
        } catch (ScriptRecurentException exception) {
            ConsoleManager.printErr("No recurrent usage pls!");
        } catch (IllegalStateException exception) {
            ConsoleManager.printErr("Unexpected!");
            System.exit(0);
        } catch (BadAccessToFileException e) {
            ConsoleManager.printErr("No rules");
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

}
