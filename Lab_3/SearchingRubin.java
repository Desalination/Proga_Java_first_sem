package com.Lab_3;

import java.util.Objects;

public class SearchingRubin implements iSearchingRubin{

    private Gem KingRubin = new Gem("king of rubies", Colors.RED, "largest among rubies", false);
    public void start(Seeker seeker) throws IllegalSeeker{
//        if(!(seeker.getItem().equals(KingRubin)))
//            throw new IllegalSeeker(seeker.getName() + " isn't searching " + KingRubin.toString() + ".\n"); For Lab4
        boolean state = true;
        int loc_ind;
       // Main.out.add((String)seeker.getTiredness());
        if(seeker.getTiredness() == 10){//go to rest
            seeker.say("My companion, go to rest on Moon.");
            seeker.jump_on_smth(seeker.getPet().getName());
            seeker.getPet().flying_to(Locations.MOON);
            seeker.changeLocation(seeker, seeker.getPet(), Locations.MOON);
            seeker.lie_next_to_smth("crater");
            seeker.getPet().resting_next_to_smth(seeker.getName());
            seeker.notice_smth(seeker.getItem().toString(), "on Earth's surface");
            seeker.say("Hooray! I found the King of Rubies, who’s been gone for hundreds of years!");
            seeker.setTiredness(0);
            seeker.jump_up();
            seeker.throw_away_things(seeker.getGems(), "they not intereseted " + seeker.getName());
            seeker.put_on_smth(Clothes.GLOTHES, Clothes.COAT);
            seeker.say("On our way, my companion, in half an hour we will have the King of Rubies in our hands!");
            seeker.jump_on_smth(seeker.getPet().getName());
            seeker.getPet().flew();
            seeker.changeLocation(seeker, seeker.getPet(), Locations.SPACE);
            seeker.getPet().flying_to(Locations.EARTH);
            state = false;
                   }
        if(state){
            Main.out.add(seeker.getName() + " goes in search of the " + seeker.getItem().toString() + ".\n");
            loc_ind = (int)Math.floor(Math.random() * (Locations.values().length - 2));
        }
        else loc_ind = 1;
        switch (loc_ind){
            case  (0):
                baseic_searching(seeker, Locations.NONIDENTIFIED, state);
                bad_end(seeker);
                break;
            case (1):
                baseic_searching(seeker, Locations.EARTH, state);
                if(Math.random() > 0.5){
                    good_end(seeker);
                }
                else
                    bad_end(seeker);
                break;
            case (2):
                baseic_searching(seeker, Locations.MOON, state);
                bad_end(seeker);
                break;
        }
    }

    private void baseic_searching(Seeker seeker, Locations location, boolean state) {
        if(state) {
            seeker.jump_on_smth(seeker.getPet().getName());
            seeker.say("Go to " + location.toString() + ", my companion, for looking for " + seeker.getItem().toString() + ".\n");
            seeker.getPet().flying_to(location);
        }
        seeker.getPet().land(seeker.getPet(),seeker,location);
//        seeker.changeLocation(seeker, seeker.getPet(),location);
        seeker.searching();
    }
    private void bad_end(Seeker seeker){
        seeker.setCondition(HumanConditions.UPSET, "found nothing");
        seeker.changeLocation(seeker, seeker.getPet(), Locations.HOME);
    }

    private void good_end(Seeker seeker){
        seeker.setCondition(HumanConditions.HAPPY, "he found " + seeker.getItem().toString());
        seeker.take_thing(seeker.getItem(), seeker.getGems());
        seeker.setFound();
    }

    public String toString() {return ("Searching of" + KingRubin.getName());}

    public int hashCode() {
        return Objects.hash(KingRubin.getName() + 152);
    }
    public boolean equals(Object that) {
        if (this == that) {return true;}
        if (!(that instanceof SearchingRubin)) {return false;}
        SearchingRubin srubin = (SearchingRubin) that;
        return (KingRubin.getName() == srubin.KingRubin.getName());
    }
}
