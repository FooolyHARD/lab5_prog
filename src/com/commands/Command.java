package com.commands;

import java.util.Objects;

//Abstr cmd class for another cmd:
public abstract class Command {
    private String name;
    private String disc;

    public Command(String name, String disc){
        this.name = name;
        this.disc = disc;
    }
    //Execute status (boolean):
    public abstract boolean execute(String arg);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(disc, command.disc);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", disc='" + disc + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return name.hashCode() + disc.hashCode();
    }

}