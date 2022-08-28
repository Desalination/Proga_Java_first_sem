package com.Lab_4;

public interface iLifeForm{
    LifeFormTypes getType();
    String getName();
    Locations getLocation();
    void setLocation(Locations location);
    void changeLocation(Locations location);
    void changeLocation(LifeForm lf1, LifeForm lf2,Locations location);

}
