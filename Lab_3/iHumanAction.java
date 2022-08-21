package com.Lab_3;

import java.util.ArrayList;

public interface iHumanAction<T>  {
    void say(String phrase);
    void jump_up();
    void put_on_smth(String s);
    void put_on_smth(Clothes cloth1, Clothes cloth2);
    void jump_on_smth(String s);
    void notice_smth(String s);
    void notice_smth(String s, String where);
    void lie_next_to_smth(String s);
    void searching();
    void go_home();
    void throw_away_things(ArrayList<T> gems, String reason);
    void throw_away_things(ArrayList<T> gems);
}
