package com.IO;

import com.Main;
import com.exceptions.BadScriptException;
import com.exceptions.IncorrectLengthException;
import com.exceptions.NotNullException;
import com.exceptions.WrongNameException;
import com.sourcefiles.Coordinates;
import com.sourcefiles.FuelType;
import com.sourcefiles.VehicleType;
import com.sun.corba.se.spi.oa.ObjectAdapter;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerManager {
    private final double MAX_X = 829;
    private final int MIN_Y = -617;
    private final int MIN_HEIGHT = 0;
    private final int MAX_HEIGHT = 300;
    private Pattern patternNumber = Pattern.compile("-?\\d+(\\.\\d+)?");
    private Pattern patternSymbols = Pattern.compile("^[A-Z][a-z]*(\\\\s(([a-z]{1,3})|(([a-z]+\\\\')?[A-Z][a-z]*)))*$");
    private Scanner userScanner;
    private boolean filemode;
    public ScannerManager(Scanner scanner){
        this.userScanner = scanner;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }
    public void setFileMode() {
        filemode = true;
    }

    public void setUserMode() {
        filemode = false;
    }
    public Scanner getUserScanner() {
        return userScanner;
    }
    //TODO: для перснованольного ввода
    public String askName(String inputTitle, int minLength, int maxLength, String typeOfName) throws BadScriptException {
        String name;
        while (true) {
            try {
                System.out.println(inputTitle);
                System.out.print(Main.INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (filemode) System.out.println(name);
                if (name.equals("")) throw new NotNullException();
                if (name.length() <= minLength) throw new IncorrectLengthException();
                if (name.length() >= maxLength) throw new IncorrectLengthException();
                if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
                break;
            } catch (WrongNameException exception) {
                ConsoleManager.printErr(String.format("%s must be char symbol!", typeOfName));
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr(String.format("%s didnt find!", typeOfName));
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NotNullException exception) {
                ConsoleManager.printErr(String.format("%s cant be empty!", typeOfName));
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            } catch (IncorrectLengthException e) {
                ConsoleManager.printErr(String.format("Length must lay in (%d; %d)", minLength, maxLength));
            }
        }
        return name;
    }


    public String askPersonName() throws BadScriptException {
        return askName("Enter Vehicle name:", 0, Integer.MAX_VALUE, "Vehicle name");
    }


    public double askXOfCoordinates(boolean withLimit) throws BadScriptException {
        String strX = "";
        double x;
        while (true) {
            try {
                if (withLimit)
                    System.out.println("Enter X coord :");
                else
                    System.out.println("Enter X coord:");
                System.out.print(Main.INPUT_INFO);
                strX = userScanner.nextLine().trim();
                if (filemode) System.out.println(strX);
                x = Double.parseDouble(strX);
                if (withLimit && x >= MAX_X) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect X coord");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IncorrectLengthException exception) {
                ConsoleManager.printErr("Coord X must lay on (" + 0
                        + ";" + (withLimit ? MAX_X : Double.MAX_VALUE) + ")!");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(strX).matches())
                    ConsoleManager.printErr("Coord X must lay on (" + Double.MAX_VALUE
                            + ";" + (withLimit ? MAX_X : Double.MAX_VALUE) + ")!");
                else
                    ConsoleManager.printErr("Coord X must be an double");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }


    public int askYOfCoordinates() throws BadScriptException {
        String strY = "";
        int y;
        while (true) {
            try {
                System.out.println("Enter Y coord:");
                System.out.print(Main.INPUT_INFO);
                strY = userScanner.nextLine().trim();
                if (filemode) System.out.println(strY);
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect Y coord");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(strY).matches()) //why
                    ConsoleManager.printErr("Coord Y must lay on (" + MIN_Y
                            + ";" + Integer.MAX_VALUE + ")!");
                else
                    ConsoleManager.printErr("Coord X must be an int");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }


    public Coordinates askCoordinates() throws BadScriptException {
        double x = askXOfCoordinates(true);
        int y = askYOfCoordinates();
        return new Coordinates();
    }


    public int askEnginePower() throws BadScriptException {
        String strHeight = "";
        int height;
        while (true) {
            try {
                System.out.println("Enter engine power");
                System.out.print(Main.INPUT_INFO);
                strHeight = userScanner.nextLine().trim();
                if (filemode) System.out.println(strHeight);
                height = Integer.parseInt(strHeight);
                if (height <= 0) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect engine power!");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                    ConsoleManager.printErr("Engine power must be an int!");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            } catch (IncorrectLengthException e) {
                ConsoleManager.printErr("Engine power must lay on (" + 0 + ";" + "+inf" + ")!");
            }
        }
        return height;
    }


    public VehicleType askVehicleType() throws BadScriptException {
        String strVehtype;
        VehicleType type;
        while (true) {
            try {
                System.out.println("Types list - " + VehicleType.typeList());
                System.out.println("Enter your type:");
                System.out.print(Main.INPUT_INFO);
                strVehtype = userScanner.nextLine().trim();
                if (filemode) System.out.println(strVehtype);
                type = VehicleType.valueOf(strVehtype.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown type");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException exception) {
                ConsoleManager.printErr("No such type in list");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return type;
    }

    public FuelType askFuelType() throws BadScriptException {
        String strFueltype;
        FuelType fuelType;
        while (true) {
            try {
                System.out.println("List of fuel types - " + FuelType.fuelList());
                System.out.println("Enter your fuel type");
                System.out.print(Main.INPUT_INFO);
                strFueltype = userScanner.nextLine().trim();
                if (filemode) System.out.println(strFueltype);
                fuelType = FuelType.valueOf(strFueltype.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown fuel type");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException exception) {
                ConsoleManager.printErr("No such fuel type in list");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return fuelType;
    }
    public boolean askQuestion(String question) throws BadScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print(Main.INPUT_INFO);
                answer = userScanner.nextLine().trim();
                if (filemode) System.out.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown answer!");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IncorrectLengthException exception) {
                ConsoleManager.printErr("Answer must be + or -!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }


}
