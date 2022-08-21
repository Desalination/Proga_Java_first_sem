package com.Lab_3;

public interface iHumanCondition {

    public void setCondition(HumanConditions condition);
//    public void setCondition(HumanConditions condition, boolean display);
    public void setCondition(HumanConditions condition, String cause);
    public void setTiredness(int tiredness);
    public int getTiredness();
}
