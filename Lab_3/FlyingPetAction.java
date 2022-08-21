package com.Lab_3;

public abstract class FlyingPetAction extends LifeForm implements iFlyingPetAction{

    public FlyingPetAction(LifeFormTypes type){
        super(type,"");
    }

    public FlyingPetAction(LifeFormTypes type, String name){
        super(type, name);
    }

    public FlyingPetAction(LifeFormTypes type, Locations location){
        super(type,location);
    }
    public FlyingPetAction(LifeFormTypes type, String name, Locations location){
        super(type, name, location);
    }
    public void resting_next_to_smth(String s){
        Main.out.add(getType() + " " + this.getName() + " is resting next to " + s +".\n");
    }
    public void flew(){
        Main.out.add(getType() + " " + this.getName() + " flew " +".\n");
    }
    public void flying_to(Locations where){
        Main.out.add(getType() + " " + this.getName() + " is flying to " + where.toString() + ".\n");
    }

    public void land(Locations where){
        this.changeLocation(where);
        Main.out.add(getType() + " " + this.getName() + " landed on " + where.toString() + ".\n");
    }
    public void land(LifeForm lf1, LifeForm lf2, Locations where){
        this.changeLocation(lf1, lf2, where);
        Main.out.add(getType() + " " + this.getName() + " landed on " + where.toString() + ".\n");
    }
}
