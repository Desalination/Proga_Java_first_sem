package com.Lab_3;

import java.util.Objects;

public abstract class LifeForm implements iLifeForm {
    private final LifeFormTypes type;
    private final String name;
    private Locations location;

    public LifeForm(LifeFormTypes type){
        this.type = type; this.name = "";
    }

    public LifeForm(LifeFormTypes type, String name){
        this.type = type; this.name = name;
    }

    public LifeForm(LifeFormTypes type, Locations location){
        this.type = type; this.name = ""; this.location = location;
        Main.out.add(this.type + " in " + location.toString() + ".\n");
    }
    public LifeForm(LifeFormTypes type, String name, Locations location){
    this.type = type; this.name = name; this.location = location;
    Main.out.add(this.type + " " + this.name + " in " + location.toString() + ".\n");
    }


    public void setLocation(Locations location){
        this.location = location;
        Main.out.add(this.type + " " + this.name + " in " + location.toString() + ".\n");
    }


    public void changeLocation(Locations location){
        if(this.location != null){
        Main.out.add(this.type + " " + this.name + " moved from "+ this.location.toString()+ " to " + location.toString() + ".\n");
        this.location = location;
        }
        else setLocation(Locations.NONIDENTIFIED);
    }

    public void changeLocation(LifeForm lf1, LifeForm lf2,Locations location){
        if(this.location != null){
            Main.out.add(this.getType() + " " + this.name + " and " + lf2.getType() + " " + lf2.getName() + " moved to " + location.toString() + ".\n");
            this.location = location;
            lf2.location = location;
        }
        else {setLocation(Locations.NONIDENTIFIED);lf2.setLocation(Locations.NONIDENTIFIED);}
    }

    public void go_home(){
        changeLocation(Locations.HOME);
    }

    public LifeFormTypes getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public Locations getLocation(){
        return this.location;
    }

    public String toString() {return getType() + " " + getName();}

    public int hashCode() {
        return Objects.hash(name);
    }
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof LifeForm)) return false;
        LifeForm lf = (LifeForm) that;
        return Objects.equals(name, lf.name);
    }
}
