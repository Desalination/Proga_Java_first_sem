package com.Lab_4;

import java.util.ArrayList;
import java.util.Objects;

public class Seeker<T> extends HumanWithPet implements iSeeker{

    private Bag<T> itemsBag;
    private Pet pet;
    private T item;
    private boolean found;

    Seeker(LifeFormTypes type, String name, Locations location, Pet pet, T item, Bag<T> items, int tiredness, boolean f){
        super(type, name , location, pet);
        this.itemsBag = items;
        Main.out.add(this.getName() + " have a bag[" + itemsBag.id + "].\n");
        this.itemsBag.show_bag();
        this.item = item;
        this.found = f;
        if(!found){
            Main.out.add("Seeker " + this.getName() + " is looking for " + this.item.toString() + ".\n");
        }
        setTiredness(tiredness);
        if(tiredness >= 10 && !found) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }
    Seeker(LifeFormTypes type, String name, Locations location, Pet pet, T item, Bag<T> items, int tiredness){
        super(type, name , location, pet);
        this.itemsBag = items;
        Main.out.add(this.getName() + " have a bag[" + itemsBag.id + "].\n");
        this.itemsBag.show_bag();
        this.item = item;
        this.found = false;

        if(!found){
            Main.out.add("Seeker " + this.getName() + " is looking for " + this.item.toString() + ".\n");
        }
        else
            Main.out.add("Seeker " + this.getName() + " already have found " + this.item.toString() + ".\n");
        setTiredness(tiredness);
        if(tiredness >= 10 && !found) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }
    Seeker(LifeFormTypes type, String name, Locations location, T item, Bag<T> items, int tiredness, boolean f){
        super(type, name , location);
        this.itemsBag = items;
        Main.out.add(this.getName() + " have a bag[" + itemsBag.id + "].\n");
        this.itemsBag.show_bag();
        this.item = item;
        this.found = f;
        if(!found){
            Main.out.add("Seeker " + this.getName() + " is looking for " + this.item.toString() + ".\n");
        }
        else
            Main.out.add("Seeker " + this.getName() + " already have found " + this.item.toString() + ".\n");
        setTiredness(tiredness);
        if(tiredness >= 10 && !found) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }

    Seeker(LifeFormTypes type, String name, Locations location, T item, int tiredness, boolean f){
        super(type, name , location);
        this.itemsBag = new Bag();
        Main.out.add(this.getName() + " have a bag[" + itemsBag.id + "].\n");
        this.item = item;
        this.found = f;
        if(!found){
            Main.out.add("Seeker " + this.getName() + " is looking for " + this.item.toString() + ".\n");
        }
        else
            Main.out.add("Seeker " + this.getName() + " already have found " + this.item.toString() + ".\n");
        setTiredness(tiredness);
        if(tiredness >= 10 && !found) {
            this.setCondition(HumanConditions.THINK_TO_FINISH_SEARCHING);
        }
    }

    public void setItem(T gem){this.item = gem;}

    public void setFound() {this.found = true;}
    public T getItem(){
        return  this.item;
    }
    public Bag<T> getBag(){
        return itemsBag;
    }


    public static class Bag<T>{
        private ArrayList<T> bag;
        private static int id = 0;

        Bag(){
            ArrayList<T> bag_ = new ArrayList<>();
            this.bag = bag_;
            id++;
        }
        Bag(T thing){
            ArrayList<T> bag_ = new ArrayList<>();
            this.bag = bag_;
            bag.add(thing);
            id++;
        }
        Bag(ArrayList<T> bag_){
            this.bag = bag_;
            id++;
        }

        public void throw_away_things(String reason) {
            if (!(bag.isEmpty())) {
                Main.out.add("From bag[" + id + "] are thrown away ");
                for (T gem : bag) {
                    Main.out.add("\"" + gem.toString() + "\"");
                    if (gem != bag.get(bag.size() - 1)) Main.out.add(", ");
                }
                Main.out.add(" because " + reason + ".\n");
                bag.clear();
            }
        }

        public void throw_away_things() {
            if (!(bag.isEmpty())) {
                Main.out.add("From bag[" + id + "] are thrown away ");
                for (T gem : bag) {
                    Main.out.add("\"" + gem.toString() + "\"");
                    if (gem != bag.get(bag.size() - 1)) Main.out.add(", ");
                }
                Main.out.add(".\n");
                bag.clear();
            }
        }

        public void take_thing(T thing) {
            bag.add(thing);
            Main.out.add("In bag[" + this.id + "] added \"" + thing.toString() + "\".\n");
        }


        public void open(){
            Main.out.add("Bag[" + id + "] was opened.\n");
        }
        public void show_bag() {
            if (!(bag.isEmpty())) {
                Main.out.add("There are in bag[" + this.id + "]: ");
                for (T gem : bag) {
                    Main.out.add("\"" + gem.toString() + "\"");
                    if (gem != bag.get(bag.size() - 1)) Main.out.add(", ");
                }
                Main.out.add(".\n");
            }
        }
        public int getId(){return id;}
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Seeker)) return false;
        Seeker lf = (Seeker) that;
        return Objects.equals(this.getName(), lf.getName());
    }
}
