package com.Lab_4;

import java.util.ArrayList;

public class Human<T> extends HumanCondition {//inner class
    HumanAction action;
    private boolean lying = false;

    Human(LifeFormTypes type, String name, Locations location) {
        super(type, name , location, HumanConditions.NORM);
        action = new HumanAction();
    }

    Human(LifeFormTypes type, String name, Locations location, HumanConditions condition){
        super(type, name , location, condition);
        action = new HumanAction();
    }


    public class HumanAction<T> implements iHumanAction<T> {

        public void say(String phrase) {
            Main.out.add(getName() + " says: \"" + phrase + "\"\n");
        }

        public void jump_up() {
            Main.out.add(getName() + " jumped up.\n");
            lying = false;
        }

        public void put_on_smth(String s) {
            Main.out.add(getName() + " put on " + s + ".\n");
        }

        public void put_on_smth(Clothes cloth1, Clothes cloth2) {
            Main.out.add(getName() + " put on " + cloth1.toString() + ", " + cloth2.toString() + ".\n");
        }

        public void jump_on_smth(String s) {
            if (s.equals("none")) {
                Main.out.add("Ups. " + getName() + "haven't this thing.\n");
            } else Main.out.add(getName() + " jumped on his " + s + ".\n");
            lying = false;
        }

        public void notice_smth(String s) {
            Main.out.add(getName() + " noticed " + s + ".\n");
        }

        public void notice_smth(String s, String where) {
            Main.out.add(getName() + " noticed " + s + " " + where + ".\n");
        }

        public void lie_next_to_smth(String s) throws UnableToLie{
            if(lying) throw new UnableToLie(this.toString() + " is already lying!\n");
            Main.out.add(getName() + " lied next to " + s + ".\n");
            lying = true;
        }

        public void searching() {
            Main.out.add(getName() + " is searching.\n");
        }

        public void open_smth(String thing){
            Main.out.add(getName() + " opened " + thing + ".\n");
        }
    }


}
