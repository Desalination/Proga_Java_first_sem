package com.Lab_4;

public interface iSearchingRubin {
    void start(Seeker seeker) throws IllegalSeeker, UnableToLie;
   private void baseic_searching(Seeker seeker, Locations location, boolean state){
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
        seeker.changeLocation(seeker,seeker.getPet(), Locations.HOME);
    }
    private void good_end(Seeker seeker){
        seeker.setCondition(HumanConditions.HAPPY, "found " + seeker.getItem().toString());
        seeker.getBag().take_thing(seeker.getItem());
    }
    void EarthScript();
}
