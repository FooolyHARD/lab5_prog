package com.sourcefiles;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


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
