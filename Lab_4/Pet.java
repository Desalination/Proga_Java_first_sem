package com.Lab_4;

public class Pet extends FlyingPetAction implements iPet {

    private final Colors color;
    private HumanWithPet owner;
    Pet(LifeFormTypes type, String name, Colors color, Locations location, HumanWithPet owner){
        super(type, name);this.color = color; setOwner(owner); super.setLocation(location);
        Main.out.remove(Main.out.size()-1);
        Main.out.add(color + " " + getType() + " " + getName() + " in " + location.toString() + ".\n");
    }

    Pet(LifeFormTypes type, String name, Colors color, Locations location){
        super(type, name, location); this.color = color;
        Main.out.remove(Main.out.size()-1);
        Main.out.add(color + " " + getType() + " " + getName() + " in " + location.toString() + ".\n");
    }

    Pet(LifeFormTypes type, Colors color, Locations location, HumanWithPet owner){
        super(type);this.color = color; setOwner(owner); super.setLocation(location);
        Main.out.remove(Main.out.size()-1);
        Main.out.add(color + " " + getType() + " " + getName() + " in " + location.toString() + ".\n");
    }

    Pet(LifeFormTypes type, Colors color, Locations location){
        super(type, location); this.color = color;
        Main.out.remove(Main.out.size()-1);
        Main.out.add(color + " " + getType() + " " + getName() + " in " + location.toString() + ".\n");
    }
    public void setOwner(HumanWithPet human){
        Main.out.add(human.getName() + " has a pet " + color + " " + getType() + " " + getName() + ".\n");
        this.owner = human;
        if(human.getPet() == null || !(human.getPet().equals(this))) human.setPet(this);
    }

    public HumanWithPet getOwner(){
        return this.owner;
    }


    public String toString(){
        return (color + " " + this.getType() + " " + this.getName());
    }
}
