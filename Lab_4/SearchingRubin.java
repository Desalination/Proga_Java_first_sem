package com.Lab_4;

import java.util.Objects;

public class SearchingRubin implements iSearchingRubin {
    private Gem KingRubin = new Gem("King of rubies", Colors.RED, "largest among rubies", false);
    public void start(Seeker seeker) throws IllegalSeeker, UnableToLie{
        if(!(seeker.getItem().equals(KingRubin)))
            throw new IllegalSeeker(seeker.getName() + " isn't searching " + KingRubin.toString() + ".\n");
        boolean state = true;
        int loc_ind;
        if(seeker.getTiredness() == 10){//go to rest

            seeker.action.say("My companion, go to rest on Moon.");
            seeker.action.jump_on_smth(seeker.getPet().toString());
            seeker.getPet().flying_to(Locations.MOON);
            seeker.changeLocation(seeker, seeker.getPet(), Locations.MOON);
            seeker.action.lie_next_to_smth("crater");
            seeker.getPet().resting_next_to_smth(seeker.getName());
            seeker.action.notice_smth(seeker.getItem().toString(), "on Earth's surface");
            seeker.action.say("Hooray! I found the King of Rubies, who’s been gone for hundreds of years!");
            seeker.setTiredness(0);
            seeker.action.jump_up();
            seeker.getBag().throw_away_things("they not intereseted " + seeker.getName());
            seeker.action.put_on_smth(Clothes.GLOTHES, Clothes.COAT);
            seeker.action.say("On our way, my companion, in half an hour we will have the King of Rubies in our hands!");
            seeker.action.jump_on_smth(seeker.getPet().toString());
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
            seeker.action.jump_on_smth(seeker.getPet().getName());
            seeker.action.say("Go to " + location.toString() + ", my companion, for looking for " + seeker.getItem().toString() + ".\n");
            seeker.getPet().flying_to(location);
        }
        seeker.getPet().land(seeker.getPet(),seeker,location);
//        seeker.changeLocation(seeker, seeker.getPet(),location);
        seeker.action.searching();
    }
    private void bad_end(Seeker seeker){
        seeker.setCondition(HumanConditions.UPSET, "found nothing");
        seeker.changeLocation(seeker, seeker.getPet(), Locations.HOME);
    }

    private void good_end(Seeker seeker){
        seeker.setCondition(HumanConditions.HAPPY, "he found " + seeker.getItem().toString());
        seeker.getBag().take_thing(seeker.getItem());
        seeker.setFound();
    }


    public void EarthScript(){
        boolean t = true;
        iTime time = new iTime(){//anonim class
            @Override
            public void setNight(){
            Main.out.add(dayTime.NIGHT.toString() + " fell.\n");
            }
        };
        time.setNight();
        Seeker TofslaVifsla = new Seeker(LifeFormTypes.UNKNOWNTYPE, "Tofsla and Visla", Locations.EARTHGARDEN, KingRubin, new Seeker.Bag<Gem>(KingRubin), 0, t);
//        TofslaVifsla.getBag().take_thing(KingRubin);
        TofslaVifsla.action.open_smth("Bag");
        TofslaVifsla.getBag().open();
        KingRubin.action.shining(Colors.PINK);
        Surround crowd = new Surround(); crowd.surround("Crowd", Locations.EARTHGARDEN, KingRubin);
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
