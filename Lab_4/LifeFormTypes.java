package com.Lab_4;

public enum LifeFormTypes {
    HUMAN{
        public String toString(){
            return "Human";
        }
    },
    PANTERA{
        public String toString(){
            return "Pantera";
        }
    },
    UNKNOWNTYPE{
        public String toString(){ return "Unknown type life form";}
    }
}
