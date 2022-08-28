package com.Lab_4;

public class Surround<T> implements  iSurround<T>{

    public void surround(String name, Locations location, T target){
        class GroupOfLifeForms{
            private String name;
            private Locations location;

            public GroupOfLifeForms(String name, Locations location) {
                this.name = name;
                this.location = location;
                Main.out.add(this.name + " in " + location.toString() + ".\n");
            }

            public String getName() {
                return name;
            }

            public Locations getLocation() {
                return location;
            }

        }
        GroupOfLifeForms GLF = new GroupOfLifeForms(name, location);
        Main.out.add(GLF.getName() + " is surrounding " + target.toString() + ".\n");
    }
}
