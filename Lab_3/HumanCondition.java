package com.Lab_3;

public abstract class HumanCondition extends HumanAction  implements  iHumanCondition{
    private HumanConditions condition;
    private int tiredness;


    HumanCondition(LifeFormTypes type, String name, Locations location){
        super(type, name , location);this.condition = HumanConditions.NORM;
    }

    HumanCondition(LifeFormTypes type, String name, Locations location, HumanConditions condition){
        super(type, name , location); this.condition = condition;
    }

    public void setCondition(HumanConditions condition){
        this.condition = condition;
        Main.out.add(super.getName() + " " + condition.toString() + ".\n");
    }
//    public void setCondition(HumanConditions condition, boolean display){
//        this.condition = condition;
//        if(display) Main.out.add(super.getName() + " " + condition.toString() + ".\n");
//    }
    public void setCondition(HumanConditions condition, String cause){
        this.condition = condition;
        Main.out.add(super.getName() + " " + condition.toString() + " because " + cause + ".\n");
    }


    public void setTiredness(int tiredness) {
        if(tiredness >= 10){
            this.tiredness = 10;
            this.setCondition(HumanConditions.EXHAUSTED);
        }
        else if(tiredness <= 0){
            this.tiredness = 0;
            Main.out.add(this.getName() + " is full of energy.\n");
        }
        else{
            this.tiredness = tiredness;
            Main.out.add(this.getName() + " have energy: " + (10-tiredness) + ".\n");
        }
    }

    public HumanConditions getCondition() {
        return this.condition;
    }
    public int getTiredness(){
        return this.tiredness;
    }


}
