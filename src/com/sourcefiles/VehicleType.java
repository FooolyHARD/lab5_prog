package com.sourcefiles;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


public enum VehicleType {
    PLANE,
    DRONE,
    BOAT,
    MOTORCYCLE,
    HOVERBOARD,
    CAR;
    public static String typeList() {
        StringBuilder types = new StringBuilder();
        for (VehicleType type : values()) {
            types.append(type.name()).append(";");
        }
        return types.substring(0, types.length() - 2);
    }

}

