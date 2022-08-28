package com.Lab_4;


public interface iHumanAction<T>  {
    void say(String phrase);
    void jump_up();
    void put_on_smth(String s);
    void put_on_smth(Clothes cloth1, Clothes cloth2);
    void jump_on_smth(String s);
    void notice_smth(String s);
    void notice_smth(String s, String where);
    void lie_next_to_smth(String s)throws UnableToLie;
    void searching();
}
