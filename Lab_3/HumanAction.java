package com.Lab_3;

import java.util.ArrayList;

public abstract class HumanAction<T> extends LifeForm implements iHumanAction<T>{


    HumanAction(LifeFormTypes type, String name, Locations location){
        super(type, name , location);
    }


    public void say(String phrase){ Main.out.add(this + " says: \"" + phrase + "\"\n");}
    public void jump_up(){
        Main.out.add(super.getName() + " jumped up.\n");
    }

    public void put_on_smth(String s){
        Main.out.add(super.getName() + " put on "+ s +".\n");
    }

    public void put_on_smth(Clothes cloth1, Clothes cloth2){
        Main.out.add(super.getName() + " put on "+ cloth1.toString() + ", " + cloth2.toString() +".\n");
    }

    public void jump_on_smth(String s){
        if(s.equals("none")){Main.out.add("Ups. " + super.getName() + "haven't this thing.\n");}
        else Main.out.add(super.getName() + " jumped on his " + s + ".\n");
    }

    public void notice_smth(String s){
        Main.out.add(super.getName() + " noticed " + s + ".\n");
    }
    public void notice_smth(String s, String where){
        Main.out.add(this.getName() + " noticed " + s + " " + where + ".\n");
    }

    public void lie_next_to_smth(String s){
        Main.out.add(this.getName() + " lied next to " + s + ".\n");
    }

    public  void searching(){Main.out.add(this.getName() + " is searching.\n");}

    public void throw_away_things(ArrayList<T> gems, String reason){
        if(!(gems.isEmpty())) {
            Main.out.add(this.getName() + " threw away ");
            for (T gem : gems) {
                Main.out.add("\"" + gem.toString() + "\"");
                if (gem != gems.get(gems.size() - 1)) Main.out.add(", ");
            }
            Main.out.add(" because " + reason + ".\n");
            gems.clear();
        }
    }

    public void throw_away_things(ArrayList<T> gems) {
        if (!(gems.isEmpty())) {
            Main.out.add(this.getName() + " threw away ");
            for (T gem : gems) {
                Main.out.add("\"" + gem.toString() + "\"");
                if (gem != gems.get(gems.size() - 1)) Main.out.add(", ");
            }
            Main.out.add(".\n");
            gems.clear();
        }
    }

    public void take_thing( T thing, ArrayList<T> bag){
        bag.add(thing);
        Main.out.add("In bag of " + this.getType() + " " + this.getName() + " added \"" + thing.toString() + "\".\n");
    }

    public void show_bag(ArrayList<T> bag){
        if (!(bag.isEmpty())) {
            Main.out.add(this.getName() + " have in bag: ");
            for (T gem : bag) {
                Main.out.add("\"" + gem.toString() + "\"");
                if (gem != bag.get(bag.size() - 1)) Main.out.add(", ");
            }
            Main.out.add(".\n");
        }
    }
}


