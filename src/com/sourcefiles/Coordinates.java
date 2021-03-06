package com.sourcefiles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
public class Coordinates {
    private double x; //Максимальное значение поля: 829, Поле не может быть null
    private int y; //Значение поля должно быть больше -671


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
