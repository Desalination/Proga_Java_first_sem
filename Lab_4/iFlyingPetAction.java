package com.Lab_4;

public interface iFlyingPetAction {
    void resting_next_to_smth(String s);
    void flew();
    void flying_to(Locations where);
    void land(Locations where);
    void land(LifeForm lf1, LifeForm lf2, Locations where);
}
