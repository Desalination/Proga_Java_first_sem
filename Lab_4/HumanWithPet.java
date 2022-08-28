package com.Lab_4;

public class HumanWithPet extends Human implements iHumanWithPet{
    private Pet pet;
    HumanWithPet(LifeFormTypes type, String name, Locations location, Pet pet) {
        super(type, name , location, HumanConditions.NORM); setPet(pet);
    }

    HumanWithPet(LifeFormTypes type, String name, Locations location, HumanConditions condition, Pet pet){
        super(type, name , location, condition); setPet(pet);
    }
    HumanWithPet(LifeFormTypes type, String name, Locations location) {
        super(type, name , location, HumanConditions.NORM);
    }

    public void setPet(Pet pet){
        this.pet = pet;
        if(pet.getOwner() == null || !(pet.getOwner().equals(this)))
            pet.setOwner(this);
    }
    public Pet getPet(){
        return this.pet;
    }

}
