package com;

import com.IO.ConsoleManager;
import com.IO.ScannerManager;
import com.commands.*;
import com.parseXML.XMLParser;
import com.utils.Chronicler;
import com.utils.CollectionManager;
import com.utils.CommandManager;
import com.utils.FileManager;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

public class Main {
    public static final String INPUT_COMMAND = "$ ";
    public static final String INPUT_INFO = "> ";


    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            Chronicler chronicler = new Chronicler();
            String fileName = "MyVehicles.xml";
            ScannerManager scannerManager = new ScannerManager(userScanner);
            FileManager fileManager = new FileManager(fileName);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new helpCommand(),
                    new infoCommand(collectionManager),
                    new showCommand(collectionManager),
                    new addCommand(collectionManager, scannerManager),
                    new updateByIdCommand(collectionManager, scannerManager),
                    new removeByIdCommand(collectionManager),
                    new clearCommand(collectionManager),
                    new saveCommand(collectionManager),
                    new executeByScriptCommand(),
                    new exitCommand(),
                    new addIfminCommand(collectionManager, scannerManager),
                    new addIfmaxCommand(collectionManager,scannerManager),
                    new historyCommand(chronicler));
                    //new removeAllByFuelType
                    //new MaxByCreationDateCommand(collectionManager),
                    //new FilterLessThanHairColorCommand(collectionManager, scannerManager),
            ConsoleManager console = new ConsoleManager(commandManager, userScanner, scannerManager, chronicler);
            console.interactiveMode();
        } catch(ArrayIndexOutOfBoundsException e){
            ConsoleManager.printErr("Incorrect PATH!");
            System.exit(0);
        }
    }
}
