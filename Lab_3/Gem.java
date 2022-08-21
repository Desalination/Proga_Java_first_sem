package com.Lab_3;

import java.util.Objects;

public class Gem implements iGem{
    private final String name;
    private final Colors color;
    private String description;

    Gem(String name, Colors color, String description){
        this.name = name;this.color = color;this.description = description;
        Main.out.add("In world exists \"" + this.color + " " + this.name + "\" which is " + description + ".\n");
    }

    Gem(String name, Colors color, String description, boolean f){
        this.name = name;this.color = color;this.description = description;
    }
    Gem(String name, Colors color){
        this.name = name;this.color = color;
    }

    public void setDescription(String description) {
        if(description.equals("null") || !this.description.equals(description)){
            this.description = description;
        }
        Main.out.add("The " + this.color + " " + this.name + " is " + description +"\n");
    }

    public String getName(){
        return this.name;
    }
    public String getColor(){return this.color.toString();}
    public String getDescription(){return this.description;}

    public String toString(){
        return ("The " + this.color.toString() + " " + this.name + " which is " + description);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Gem)) return false;
        Gem gem = (Gem) that;
        return (name == gem.name && color.toString() == gem.color.toString() && description == gem.description);
    }
}
