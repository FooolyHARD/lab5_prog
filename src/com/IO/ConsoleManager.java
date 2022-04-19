package com.IO;

import com.exceptions.BadAccessToFileException;
import com.exceptions.ScriptRecurentException;
import com.utils.CommandManager;
import com.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager {
    private final CommandManager cmdManager;
    private final Scanner scanner;
    private final ScannerManager scannerManager;
    private final List<String> scriptStack = new LinkedList<>();
    public ConsoleManager(CommandManager cmdManager, Scanner scanner, ScannerManager scannerManager) {
        this.cmdManager = cmdManager;
        this.scanner = scanner;
        this.scannerManager = scannerManager;
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
                if (!cmdManager.help(argument)) return 1;
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
