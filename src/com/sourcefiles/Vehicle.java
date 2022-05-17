package com.sourcefiles;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
public class Vehicle implements Comparable<Vehicle>{
    @NotNull
    @Positive(message = "ID должен быть больше 0")

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @NotBlank (message = "Имя не может быть пустым")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Positive(message = "Мощность двигателя должна быть больше 0")
    private float enginePower; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    @NotNull
    private FuelType fuelType; //Поле не может быть null

    public Vehicle(long id, String name, Coordinates coordinates, java.time.LocalDateTime creationDate, float enginePower,VehicleType type,FuelType fuelType ){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }
    public Long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }
    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return enginePower == vehicle.enginePower && name.equals(vehicle.name) &&
                coordinates.equals(vehicle.coordinates) && creationDate.equals(vehicle.creationDate) &&
                fuelType == vehicle.fuelType && type == vehicle.type && id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, enginePower, type, fuelType);
    }

    @Override
    public int compareTo(Vehicle other) {
        int resultByName = this.name.compareTo(other.name);
        int resultByEnginePower = (int) (this.enginePower - other.enginePower);
        int resultByType = this.type.toString().compareTo(other.type.toString());

        if (resultByName == 0) {
            if (resultByEnginePower == 0) {
                return resultByType;
            } else {
                return resultByEnginePower;
            }
        } else {
            return resultByName;
        }
    }
}