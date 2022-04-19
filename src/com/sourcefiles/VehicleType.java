package com.sourcefiles;

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

