package com.Lab_3;

public abstract class HumanWithPet extends HumanCondition implements  iHumanWithPet{


    private Pet pet;
    HumanWithPet(LifeFormTypes type, String name, Locations location){
        super(type, name , location, HumanConditions.NORM);
    }

    HumanWithPet(LifeFormTypes type, String name, Locations location, HumanConditions condition){
        super(type, name , location, condition);
    }

    public void setPet(Pet pet){
        this.pet = pet;
        if(pet.getOwner() == null || !(pet.getOwner().equals(this)))
            pet.setOwner(this);
    }
    public Pet getPet(){
        return this.pet;
    }

    public void delPet(){this.pet = null;}

}
