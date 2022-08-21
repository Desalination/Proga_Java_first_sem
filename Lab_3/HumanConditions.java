package com.Lab_3;

public enum HumanConditions {
    EXHAUSTED { public String toString(){
        return "is exhausted";
    }},
    THINK_TO_FINISH_SEARCHING { public String toString(){return "think to finish searching";}},
    UPSET{public String toString(){return ("is upset");}},
    HAPPY{public String toString(){return "is happy";}},
    NORM {public String toString(){return "is norm";}}
}
