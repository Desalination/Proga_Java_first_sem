package com.Lab_3;

import java.util.ArrayList;
import java.util.Objects;

public class Seeker<T> extends HumanWithPet implements iSeeker{

    private ArrayList<T> itemsBag;
    private T item;
    private boolean found;

    Seeker(LifeFormTypes type, String name, Locations location, T item, ArrayList<T> gems, int tiredness, boolean f){
        super(type, name , location);this.itemsBag = gems; show_bag(itemsBag); this.item = item; this.found = f;
        Main.out.add("Seeker " + getName() + "is looking for " + item.toString() + ".\n");
        setTiredness(tiredness);
        if(tiredness >= 10) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }
    Seeker(LifeFormTypes type, String name, Locations location, T item, ArrayList<T> gems, int tiredness){
        super(type, name , location);this.itemsBag = gems; show_bag(itemsBag); this.item = item; this.found = false;

        Main.out.add("Seeker " + this.getName() + "is looking for " + this.item.toString() + ".\n");
        setTiredness(tiredness);
        if(tiredness >= 10) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }



    public void setItem(T gem){this.item = gem;}

    public void setFound() {this.found = true;}

    public void addToBag(T gem) {take_thing(gem, this.itemsBag);}
    public T getItem(){
        return  this.item;
    }

    public ArrayList<T> getGems(){
        return itemsBag;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Seeker)) return false;
        Seeker lf = (Seeker) that;
        return Objects.equals(this.getName(), lf.getName());
    }
}
