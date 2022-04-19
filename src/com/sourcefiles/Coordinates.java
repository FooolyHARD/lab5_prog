package com.sourcefiles;

import java.util.Objects;

public class Coordinates {
    private double x; //Максимальное значение поля: 829, Поле не может быть null
    private int y; //Значение поля должно быть больше -671

    public Coordinates(Double x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(Double x) {
        this.x = x;
    }
    public Double getX(){
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getY(){
        return y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
