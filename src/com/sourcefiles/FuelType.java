package com.sourcefiles;

public enum FuelType {
    KEROSENE,
    ELECTRICITY,
    ALCOHOL,
    MANPOWER,
    ANTIMATTER;

    public static String fuelList(){
        StringBuilder fuelList = new StringBuilder();
        for (FuelType types: values()){
            fuelList.append(types.name()).append(";");
        }
        return fuelList.substring(0, fuelList.length()-2);
    }
}
