package com.Lab_4;

public enum Locations {

    NONIDENTIFIED{
        public String toString() {
            return "nonidentified place";
        }},
    EARTH{
        private String correction;
        public String toString() {
            return "Earth";
        }
        public String getCorrection(String s) {return s;}
        },
    MOON{
        public String toString() {
            return "Moon";
        }},
    SPACE{
        public String toString() {
            return "Space";
        }},
    EARTHGARDEN{
        public String toString() {return "Garden on Earth";}},
    HOME{
        public String toString() {
            return "Home";
        }}
}
